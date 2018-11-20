package com.numus.budgee;


import android.content.Context;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;

import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

    private static ArrayList<Transaction> data;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_expenses, container, false);
        mContext = view.getContext();
        loadData();
        rvMusicas = (RecyclerView) view.findViewById(R.id.rv_musicas);

        glm = new GridLayoutManager(mContext, 2);
        rvMusicas.setLayoutManager(glm);
        adapter = new TransactionAdapter(data);
        rvMusicas.setAdapter(adapter);

        return view;
    }

    public void loadData(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("app", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("transactions",null);
        Type type = new TypeToken<ArrayList<Transaction>>()  {}.getType();
        data = gson.fromJson(json,type);
        if (data==null){
            data = new ArrayList<>();
        }
    }

}


