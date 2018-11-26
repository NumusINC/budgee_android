package com.numus.budgee;


import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    public SummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        TextView total = view.findViewById(R.id.total);
        PieChart pieChart = view.findViewById(R.id.piechart);
        SummaryPercentage SM = new SummaryPercentage();

        // Test
        Tests test = new Tests();
        Log.d("test", test.toString());

        // CREATING DATASET
        DataManager dataManager = new DataManager(Objects.requireNonNull(getContext()));

        // Y VALUES & X VALUES
        ArrayList<Float> percentageArr = SM.percentage(dataManager.getTransactionArray());
        ArrayList<Entry> yVals = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();

        for (int i = 0; i < percentageArr.size(); i++){
            if (percentageArr.get(i) != 0f){
                yVals.add(new Entry(percentageArr.get(i), i));
                xVals.add(SM.getCategoryStore().get(i));
            }
        }

        if (SM.sameSize(yVals,xVals) != 1){
            return view;
        }

        PieDataSet dataSet = new PieDataSet(yVals, "");
        PieData data = new PieData(xVals, dataSet);

        //AssetManager am = getContext().getApplicationContext().getAssets();
        //Typeface typeface = Typeface.createFromAsset(am,String.format(Locale.US, "font/%s","gilroybold.ttf"));

        //add colors to dataset
        SM.setColors(xVals);
        ArrayList<Integer> colors = SM.getColors();
        dataSet.setColors(colors);
        data.setValueTypeface(Typeface.createFromAsset(view.getContext().getAssets(),"fonts/gilroybold.ttf" ));
        data.setValueTextSize(16f);
        data.setValueTextColor(Color.WHITE);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        pieChart.setHoleColor(getResources().getColor(R.color.colorPrimaryDark));
        pieChart.getLegend().setEnabled(false);
        pieChart.setDescription("");
        pieChart.animateXY(1400, 1400);

        //pieChart.getLegend().setTextColor(Color.WHITE);
        //pieChart.getLegend().setTextSize(12f);

        double budget = SM.getSumIn() - SM.getSumEx();
        String t = "$" + budget;

        total.setText(t);

        return view;
    }


}
