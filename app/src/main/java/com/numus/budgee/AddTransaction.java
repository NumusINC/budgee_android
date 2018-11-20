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

    ExpensesFragment expensesFragment;
    private static ArrayList<Transaction> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        expensesFragment = new ExpensesFragment();

        loadData();
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

        addTransaction(str_name,dbl_qty,category);
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("transactions",null);
        Type type = new TypeToken<ArrayList<Transaction>>()  {}.getType();
        data = gson.fromJson(json,type);
        if (data==null){
            data = new ArrayList<>();
        }
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString("transactions",json);
        editor.apply();
    }

    public void addTransaction(String name, Double qty, Transaction.Category category) {
        String[] monthName = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug.", "Sep", "Oct", "Nov", "Dec"};
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        String date = String.valueOf(dayOfMonth) + " " + monthName[cal.get(Calendar.MONTH)];
        data.add(new Transaction(name, qty, date, category));
        saveData();
    }

}
