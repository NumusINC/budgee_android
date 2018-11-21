package com.numus.budgee;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        PieChart pieChart = view.findViewById(R.id.piechart);


        // CREATING DATASET
        // Y VALUES
        ArrayList<Entry> yvalues = new ArrayList<>();
        yvalues.add(new Entry(1f, 0));
        yvalues.add(new Entry(15f, 1));
        yvalues.add(new Entry(12f, 2));
        yvalues.add(new Entry(25f, 3));

        PieDataSet dataSet = new PieDataSet(yvalues, "");

        // X VALUES
        ArrayList<String> xVals = new ArrayList<>();

        xVals.add("Transport");
        xVals.add("Food");
        xVals.add("Social");
        xVals.add("Miniso");

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
