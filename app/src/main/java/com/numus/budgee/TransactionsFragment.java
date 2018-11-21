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


/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionsFragment extends Fragment{

    private RecyclerView rvMusicas;
    private GridLayoutManager glm;
    private TransactionAdapter adapter;

    private Context mContext;
    private View view;

    DataManager dataManager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_transactions, container, false);
        mContext = view.getContext();
        dataManager = new DataManager(mContext);
        rvMusicas = (RecyclerView) view.findViewById(R.id.rv_musicas);

        glm = new GridLayoutManager(mContext, 2);
        rvMusicas.setLayoutManager(glm);
        adapter = new TransactionAdapter(dataManager.getTransactionArray());
        rvMusicas.setAdapter(adapter);

        return view;
    }

}


