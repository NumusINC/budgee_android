package com.numus.budgee;

import android.graphics.Color;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

public class SummaryPercentage {

    private String category[] = {"PET","FOOD","TAXES","HEALTH","FUN","PAYROLL","SERVICES","GROCERIES"};
    private ArrayList<String> categoryStore;
    private double sumIn = 0, sumEx = 0;
    private ArrayList<Integer> colors;

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
                case FUN:
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

    public void setColors(ArrayList<String> arrColors){
        colors = new ArrayList<>();
        for (int i = 0; i < arrColors.size(); i++){
            switch (arrColors.get(i)){
                case "PET":
                    colors.add(Color.rgb(32,204,151)); // Pet
                    break;
                case "FOOD":
                    colors.add(Color.rgb(255,169,23)); // food
                    break;
                case "TAXES":
                    colors.add(Color.rgb(57,55,74)); // taxes
                    break;
                case "HEALTH":
                    colors.add(Color.rgb(181,88,244)); // health
                    break;
                case "PAYROLL":
                    colors.add(Color.rgb(78,148,255)); // payroll
                    break;
                case "SERVICES":
                    colors.add(Color.rgb(206,154,120)); // services
                    break;
                case "GROCERIES":
                    colors.add(Color.rgb(239,76,87)); // groceries
                    break;
                case "FUN":
                    colors.add(Color.rgb(255,209,3)); // fun
                    break;
            }
        }
    }

    public int sameSize(ArrayList<Entry> yVal, ArrayList<String> xVal){
        if (yVal.size() == xVal.size()){
            return 1;
        }
        return 0;
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

    public ArrayList<Integer> getColors() {
        return colors;
    }
}
