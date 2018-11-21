package com.numus.budgee;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class AddTransaction extends AppCompatActivity {

    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        dataManager = new DataManager(getApplicationContext());
        FloatingActionButton myFab = findViewById(R.id.add);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),Dashboard.class);
                startActivity(intent);
                addItem();
            }
        });
    }

    public void addItem(){

        EditText name = findViewById(R.id.name_input);
        EditText qty  = findViewById(R.id.qty_input);
        Spinner cat = findViewById(R.id.category_input);

        String str_name = name.getText().toString();
        Double dbl_qty = Double.valueOf(qty.getText().toString());

        String cat_input = cat.getSelectedItem().toString();
        Transaction.Category category = Transaction.Category.DEFAULT;
        switch (cat_input){
            case "Groceries":
                category = Transaction.Category.GROCERIES;
                break;
            case "Payroll":
                category = Transaction.Category.PAYROLL;
                break;
            case "Health":
                category = Transaction.Category.HEALTH;
                break;
        }
        dataManager.addTransaction(str_name,dbl_qty,category);
    }

}
