package com.example.bpp.algorithms;

import java.util.ArrayList;
import java.util.List;

public class FirstFit extends BPP {

    public FirstFit(int[] itemWeights, int binCapacity){
        super(itemWeights,binCapacity);
    }

    @Override
    public List<Bin> calculate() {
        List<Bin> bins = new ArrayList<>();
        int binId = 0;
        long startTime = System.currentTimeMillis();
        for (int item : itemWeights) {
            int i;
            for (i = 0; i < bins.size(); i++) {
                Bin bin = bins.get(i);
                if (bin.capacity - bin.used >= item) {
                    bin.used += item;
                    break;
                }
            }
            if (i == bins.size()) {
                binId++;
                bins.add(new Bin(binId, binCapacity));
                bins.get(binId - 1).used = item;
            }
        }
        long endTime = System.currentTimeMillis();
        executionTime = (int)(endTime - startTime);
        return bins;
    }
}