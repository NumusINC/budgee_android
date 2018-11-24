package com.numus.budgee;

import android.content.Context;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TransactionsFragment extends Fragment{

    private RecyclerView rv_transactions;
    private GridLayoutManager glm;
    private TransactionAdapter adapter;

    private Context context;
    private View view;

    DataManager dataManager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_transactions, container, false);
        context = view.getContext();
        dataManager = new DataManager(context);
        dataManager.setDeletable(false);
        dataManager.setEditable(false);
        rv_transactions = (RecyclerView) view.findViewById(R.id.rv_musicas);

        glm = new GridLayoutManager(context, 2);
        rv_transactions.setLayoutManager(glm);
        adapter = new TransactionAdapter(dataManager.getTransactionArray());
        rv_transactions.setAdapter(adapter);

        view.findViewById(R.id.editable_button).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                dataManager.setEditable(!dataManager.getEditable());
                rv_transactions.setLayoutManager(glm);
                adapter = new TransactionAdapter(dataManager.getTransactionArray());
                rv_transactions.setAdapter(adapter);
            }
        });

        view.findViewById(R.id.erase_button).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                dataManager.setDeletable(!dataManager.isDeletable());
                rv_transactions.setLayoutManager(glm);
                adapter = new TransactionAdapter(dataManager.getTransactionArray());
                rv_transactions.setAdapter(adapter);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (dataManager.getTransactionArray().size()>0){
            view.findViewById(R.id.intro_text).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.arrow).setVisibility(View.INVISIBLE);
        }
    }

}


