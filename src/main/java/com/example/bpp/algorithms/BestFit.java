package com.example.bpp.algorithms;

import java.util.ArrayList;
import java.util.List;

public class BestFit extends BPP {


    public BestFit(int[] itemWeights, int binCapacity) {
        super(itemWeights,binCapacity);
    }

    @Override
    public List<Bin> calculate() {
        List<Bin> bins = new ArrayList<>();
        int binId = 0;

        long startTime = System.currentTimeMillis();
        for (int item : itemWeights) {
            int minBin = -1;
            int minCapacity = Integer.MAX_VALUE;

            for (int i = 0; i < bins.size(); i++) {
                Bin bin = bins.get(i);
                if (bin.capacity - bin.used >= item && bin.capacity - bin.used - item < minCapacity) {
                    minBin = i;
                    minCapacity = bin.capacity - bin.used - item;
                }
            }

            if (minBin == -1) {
                binId++;
                bins.add(new Bin(binId, binCapacity));
                bins.get(binId - 1).used = item;
            } else {
                bins.get(minBin).used += item;
            }
        }
        long endTime = System.currentTimeMillis();
        executionTime = (int)(endTime - startTime);
        return bins;
    }
}