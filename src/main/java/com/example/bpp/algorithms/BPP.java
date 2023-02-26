package com.example.bpp.algorithms;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.util.List;

public class BPP {
    protected final int[] itemWeights;
    protected final int binCapacity;
    public int executionTime;

    public BPP(int[] itemWeights, int binCapacity) {
        this.itemWeights = itemWeights;
        this.binCapacity = binCapacity;
    }

    public List<Bin> visualize(Canvas canvas, TextArea result){
        List<Bin> bins = calculate();

        StringBuilder msg = new StringBuilder("Czas działania algorytmu: "+executionTime+" ms\n");
        msg.append("Ilość użytych pudełek: "+bins.size()+"\n");
        for (Bin bin : bins) {
            msg.append("Pudełko Nr: ").append(bin.id).append(", Zajęte Miejsca: ").append(bin.used).append(", Pojemność: ").append(bin.capacity).append("\n");
        }
        result.setText(msg+"");

        int x = 0, y = 0;
        GraphicsContext ctx = canvas.getGraphicsContext2D();
        ctx.setFill(Color.LIGHTBLUE);
        ctx.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

        for (Bin bin : bins) {
            ctx.setFill(Color.WHITE);
            ctx.fillRect(x, y, 60, 30);
            ctx.setFill(Color.BLACK);
            ctx.fillText(String.valueOf(bin.used), x + 10, y + 20);
            if (x + 120 >= canvas.getWidth()) {
                x = 0;
                y += 35;
            } else x += 65;
        }
        return bins;
    }

    public List<Bin> calculate() {
        return null;
    }
}
