package com.example.bpp;

import java.util.Random;

public class Validator {
    public static int validateInt(String text) throws Exception {
        if(text.length() < 1) throw new Exception("Nie wprowadzono danych");
        int number = Integer.parseInt(text);
        if(number < 0){
            throw new Exception("Liczby nie mogą być ujemne");
        }
        return number;
    }

    public static int[] getItemsArray(int n, int min, int max, int boxSize) throws Exception{
        if(min > max){
            throw new Exception("Wartość minimalna nie może być większa od wartości maksymalnej");
        }
        if(max > boxSize){
            throw new Exception("Wartośc maksymalna musi być mniejsza od Rozmiaru Pudełek");
        }
        int[] items = new int[n];
        Random r = new Random();
        for(int i=0;i<n;i++){
            items[i] = r.nextInt(max-min+1)+min;
        }
        return items;
    }
}
