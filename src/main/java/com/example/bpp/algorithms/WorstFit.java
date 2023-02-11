package com.example.bpp.algorithms;

import java.util.ArrayList;
import java.util.List;

public class WorstFit extends BPP {

    public WorstFit(int[] itemWeights, int binCapacity) {
        super(itemWeights,binCapacity);
    }

    @Override
    public List<Bin> calculate() {
        List<Bin> bins = new ArrayList<>();
        int binId = 0;

        long startTime = System.currentTimeMillis();
        for (int item : itemWeights) {
            int maxCapacity = 0;
            int maxBinIndex = 0;
            for (int i = 0; i < bins.size(); i++) {
                Bin bin = bins.get(i);
                if (bin.capacity - bin.used >= item && bin.capacity - bin.used > maxCapacity) {
                    maxCapacity = bin.capacity - bin.used;
                    maxBinIndex = i;
                }
            }
            if (maxCapacity == 0) {
                binId++;
                bins.add(new Bin(binId, binCapacity));
                bins.get(binId - 1).used = item;
            } else {
                bins.get(maxBinIndex).used += item;
            }
        }
        long endTime = System.currentTimeMillis();
        executionTime = (int)(endTime - startTime);
        return bins;
    }
}