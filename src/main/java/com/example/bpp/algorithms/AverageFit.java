package com.example.bpp.algorithms;

import java.util.ArrayList;
import java.util.List;

public class AverageFit extends BPP{


    public AverageFit(int[] itemWeights, int binCapacity) {
        super(itemWeights,binCapacity);
    }

    private Bin getBinWithAverageFit(List<Bin> bins, int binCapacity, int item) {
        Bin selectedBin = null;
        int minDifference = Integer.MAX_VALUE;

        for (Bin bin : bins) {
            int difference = binCapacity - (bin.used + item);
            if (difference >= 0 && difference < minDifference) {
                minDifference = difference;
                selectedBin = bin;
            }
        }

        return selectedBin;
    }

    @Override
    public List<Bin> calculate() {
        List<Bin> bins = new ArrayList<>();
        int binId = 0;

        long startTime = System.currentTimeMillis();
        for (int item : AverageFit.this.itemWeights) {
            Bin bin = getBinWithAverageFit(bins, binCapacity, item);
            if (bin == null) {
                binId++;
                bin = new Bin(binId, binCapacity);
                bin.used = item;
                bins.add(bin);
            } else {
                bin.used += item;
            }
        }
        long endTime = System.currentTimeMillis();
        this.executionTime = (int) (endTime - startTime);
        return bins;
    }
}