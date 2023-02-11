package com.example.bpp.algorithms;

import java.util.ArrayList;
import java.util.List;

public class NextFit extends BPP {

    public NextFit(int[] itemWeights, int binCapacity){
        super(itemWeights, binCapacity);
    }

    @Override
    public List<Bin> calculate() {
        List<Bin> bins = new ArrayList<>();
        int binId = 0;
        int lastBin = 0;

        long startTime = System.currentTimeMillis();
        for (int item : itemWeights) {
            if (bins.isEmpty() || bins.get(lastBin).used + item > binCapacity) {
                binId++;
                bins.add(new Bin(binId, binCapacity));
                lastBin = binId - 1;
            }
            bins.get(lastBin).used += item;
        }
        long endTime = System.currentTimeMillis();
        executionTime = (int) (endTime - startTime);
        return bins;
    }
}