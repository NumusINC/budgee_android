package com.numus.budgee;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class DataManager {

    Context context;
    private static ArrayList<Transaction> data;

    public DataManager(Context context){
        this.context = context;
        loadData();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("transactions",null);
        Type type = new TypeToken<ArrayList<Transaction>>()  {}.getType();
        data = gson.fromJson(json,type);
        if (data==null){
            data = new ArrayList<>();
        }
    }

    private void saveData(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("app", Context.MODE_PRIVATE);
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

    public ArrayList<Transaction> getTransactionArray(){
        return data;
    }

}
