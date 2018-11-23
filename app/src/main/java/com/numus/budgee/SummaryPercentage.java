package com.numus.budgee;

import java.util.ArrayList;

public class SummaryPercentage {

    private String category[] = {"PET","FOOD","TAXES","HEALTH","CLOTHES","PAYROLL","SERVICES","GROCERIES","DEFAULT"};
    private ArrayList<String> categoryStore;
    private double sumIn = 0, sumEx = 0;

    public SummaryPercentage(){

    }

    public ArrayList<Float> percentage(ArrayList<Transaction> arrTest){
        Double valuesP[] = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        ArrayList<Float> valuesPR = new ArrayList<>();
        categoryStore = new ArrayList<>();

        for (int i = 0; i < arrTest.size(); i++){
            if (arrTest.get(i).getType().equals("in")){
                sumIn += arrTest.get(i).getQuantity();
            } else if (arrTest.get(i).getType().equals("ex")){
                sumEx += arrTest.get(i).getQuantity();
            }
        }

        for (int i = 0; i < arrTest.size(); i++){
            switch (arrTest.get(i).getCategory()){
                case PET:
                    valuesP[0] += (arrTest.get(i).getQuantity());
                    break;
                case FOOD:
                    valuesP[1] += (arrTest.get(i).getQuantity());
                    break;
                case TAXES:
                    valuesP[2] += (arrTest.get(i).getQuantity());
                    break;
                case HEALTH:
                    valuesP[3] += (arrTest.get(i).getQuantity());
                    break;
                case CLOTHES:
                    valuesP[4] += (arrTest.get(i).getQuantity());
                    break;
                case PAYROLL:
                    valuesP[5] += 0.0;
                    break;
                case SERVICES:
                    valuesP[6] += (arrTest.get(i).getQuantity());
                    break;
                case GROCERIES:
                    valuesP[7] += (arrTest.get(i).getQuantity());
                    break;
                case DEFAULT:
                    break;
            }
        }

        for (int i = 0; i < valuesP.length; i++) {
            if (valuesP[i] != 0) {
                double temp = (valuesP[i] * 100) / sumEx;
                valuesPR.add((float) temp);
                categoryStore.add(category[i]);
            } else {
                valuesPR.add(0f);
                categoryStore.add(category[i]);
            }
        }

        return valuesPR;
    }

    public ArrayList<String> getCategoryStore() {
        return categoryStore;
    }

    public double getSumIn() {
        return sumIn;
    }

    public double getSumEx() {
        return sumEx;
    }
}
