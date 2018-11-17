package com.numus.budgee;


import android.content.Context;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpensesFragment extends Fragment{

    private RecyclerView rvMusicas;
    private GridLayoutManager glm;
    private TransactionAdapter adapter;

    private Context mContext;
    private View view;

    public ExpensesFragment() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_expenses, container, false);
        mContext = view.getContext();

        rvMusicas = (RecyclerView) view.findViewById(R.id.rv_musicas);

        glm = new GridLayoutManager(mContext, 2);
        rvMusicas.setLayoutManager(glm);
        adapter = new TransactionAdapter(dataSet());
        rvMusicas.setAdapter(adapter);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<Transaction> dataSet() {
        ArrayList<Transaction> data = new ArrayList<>();
        String[] monthName = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug.", "Sep", "Oct", "Nov", "Dec"};
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        String date = String.valueOf(dayOfMonth) + " " + monthName[cal.get(Calendar.MONTH)];

        data.add(new Transaction("Nomina", 5999.02,date, Transaction.Category.EXTRA));
        data.add(new Transaction("Petco", 350.15,date, Transaction.Category.PET));
        data.add(new Transaction("Sams", -720.32,date, Transaction.Category.GROCERIES));
        data.add(new Transaction("Nomina", 5.02,date, Transaction.Category.EXTRA));
        data.add(new Transaction("Petco", 350.15,date, Transaction.Category.PET));
        data.add(new Transaction("Sams", -720.32,date, Transaction.Category.GROCERIES));
        data.add(new Transaction("Nomina", 5.02,date, Transaction.Category.EXTRA));
        data.add(new Transaction("Petco", 350.15,date, Transaction.Category.PET));
        data.add(new Transaction("Sams", -720.32,date, Transaction.Category.GROCERIES));
        data.add(new Transaction("Nomina", 5.02,date, Transaction.Category.EXTRA));
        data.add(new Transaction("Petco", 350.15,date, Transaction.Category.PET));
        data.add(new Transaction("Sams", -720.32,date, Transaction.Category.GROCERIES));
        data.add(new Transaction("Nomina", 5.02,date, Transaction.Category.EXTRA));
        data.add(new Transaction("Petco", 350.15,date, Transaction.Category.PET));
        data.add(new Transaction("Sams", -720.32,date, Transaction.Category.GROCERIES));


        return data;
    }


}


