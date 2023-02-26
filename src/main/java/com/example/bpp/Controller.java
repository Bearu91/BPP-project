package com.example.bpp;

import com.example.bpp.algorithms.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.List;


public class Controller {
    @FXML
    private TextField countElements, minValue, maxValue, boxSize;
    @FXML
    private TextArea resultField;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Label title;
    @FXML
    private Canvas canvas;
    @FXML
    private CheckBox checkBox;
    private int[] items;


    @FXML
    private void initialize() {
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> title.setText("Algorytm: "+newValue));
    }

    public void solve() {
        try{
            int blockSize = Validator.validateInt(boxSize.getText());
            items = Validator.getItemsArray(Validator.validateInt(countElements.getText()),Validator.validateInt(minValue.getText()),Validator.validateInt(maxValue.getText()), blockSize);

            BPP fit = switch (comboBox.getValue()) {
                case "First-Fit" -> new FirstFit(items, blockSize);
                case "Next-Fit" -> new NextFit(items, blockSize);
                case "Best-Fit" -> new BestFit(items, blockSize);
                case "Worst-Fit" -> new WorstFit(items, blockSize);
                case "Average-Fit" -> new AverageFit(items, blockSize);
                default -> null;
            };

            List<Bin> bins = fit.visualize(canvas, resultField);


            if(checkBox.isSelected()){
                DBConnection.sendInserts(bins, fit.executionTime);
            }
        }
        catch(Exception e){
            resultField.setText("Wystąpił błąd: "+e.getMessage()+"\n" );
            items = null;
        }
    }

    public void save() {
        if(items == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Zapisywanie nieudane");
            alert.setContentText("Wykonaj program zanim zapiszesz wynik");
            alert.showAndWait();
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            if(file.canWrite()){
                try{
                    FileWriter fw = new FileWriter(file);
                    fw.write(resultField.getText());
                    fw.close();
                } catch (IOException e) {
                    resultField.setText("Błąd zapisu danych");
                }
            }
        }
    }

    public void load() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            if(file.canRead()){
                try{
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        resultField.setText(resultField.getText()+line+"\n");
                    }
                } catch (FileNotFoundException e) {
                    resultField.setText("Nie znaleziono pliku");
                } catch (IOException e) {
                    resultField.setText("Błąd odczytu danych");
                }
            }
        }
    }
}