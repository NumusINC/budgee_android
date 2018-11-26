package com.numus.budgee;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

public class Tests {

    public Tests(){
    }

    @Override
    public String toString() {
        String tests = "\n" +
                "Test Not Same Size: " + testSizeN() + "\n" +
                "Test Same Size: " + testSize() + "\n" +
                "Test Fail Transaction: " + testTransactionsN() + "\n";
        return tests;
    }

    public int testSizeN(){
        SummaryPercentage SP = new SummaryPercentage();
        ArrayList<Entry> testSizeX = new ArrayList<>();
        ArrayList<String> testSizeY = new ArrayList<>();

        testSizeX.add(new Entry(12,0));
        testSizeX.add(new Entry(15,1));
        testSizeX.add(new Entry(20,2));
        testSizeX.add(new Entry(50,3));

        testSizeY.add("PET");
        testSizeY.add("FOOD");
        testSizeY.add("SERVICES");
        testSizeY.add("TAXES");
        testSizeY.add("HEALTH");

        if (SP.sameSize(testSizeX,testSizeY) == 0){
            return 1;
        }

        return 0;
    }

    public int testSize(){
        SummaryPercentage SP = new SummaryPercentage();
        ArrayList<Entry> testSizeX = new ArrayList<>();
        ArrayList<String> testSizeY = new ArrayList<>();

        testSizeX.add(new Entry(12,0));
        testSizeX.add(new Entry(15,1));
        testSizeX.add(new Entry(20,2));
        testSizeX.add(new Entry(50,3));

        testSizeY.add("PET");
        testSizeY.add("FOOD");
        testSizeY.add("SERVICES");
        testSizeY.add("TAXES");

        if (SP.sameSize(testSizeX,testSizeY) == 0){
            return 1;
        }

        return 0;
    }

    public int testTransactionsN(){
        return 0;
    }

}
