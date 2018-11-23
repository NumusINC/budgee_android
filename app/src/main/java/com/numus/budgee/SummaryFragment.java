package com.numus.budgee;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    private DataManager dataManager;
    private String category[] = {"PET","FOOD","TAXES","HEALTH","CLOTHES","PAYROLL","SERVICES","GROCERIES","DEFAULT"};
    private ArrayList<String> categoryStore;

    public SummaryFragment() {
        // Required empty public constructor
    }

    public ArrayList<Float> percentage(ArrayList<Transaction> arrTest){
        Double valuesP[] = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        ArrayList<Float> valuesPR = new ArrayList<>();
        categoryStore = new ArrayList<>();

        double sum = 0;
        for (int i = 0; i < arrTest.size(); i++){
            sum += arrTest.get(i).getQuantity();
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
                    valuesP[5] += (arrTest.get(i).getQuantity());
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
                double temp = (valuesP[i] * 100) / sum;
                valuesPR.add((float) temp);
                categoryStore.add(category[i]);
            } else {
                valuesPR.add(0f);
                categoryStore.add(category[i]);
            }
        }

        return valuesPR;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        PieChart pieChart = view.findViewById(R.id.piechart);

        // CREATING DATASET
        dataManager = new DataManager(Objects.requireNonNull(getContext()));

        // Y VALUES & X VALUES
        ArrayList<Float> percentageArr = percentage(dataManager.getTransactionArray());
        ArrayList<Entry> yVals = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();

        for (int i = 0; i < percentageArr.size(); i++){
            if (percentageArr.get(i) != 0f){
                yVals.add(new Entry(percentageArr.get(i), i));
                xVals.add(categoryStore.get(i));
            }
        }

        PieDataSet dataSet = new PieDataSet(yVals, "");
        PieData data = new PieData(xVals, dataSet);

        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        data.setValueTextSize(16f);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        pieChart.setHoleColor(getResources().getColor(R.color.colorPrimaryDark));
        pieChart.getLegend().setEnabled(false);
        pieChart.setDescription("");
        pieChart.animateXY(1400, 1400);

        return view;
    }


}
