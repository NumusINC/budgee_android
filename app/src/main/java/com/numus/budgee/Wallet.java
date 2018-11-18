package com.numus.budgee;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Wallet {

    public double budget;
    public double target;
    public long startDate;
    public long endDate;
    public String name;
    public String token;
    public double balance;
    public Context context;

    private DatabaseReference db;
    SharedPreferences userStorage;
    SharedPreferences.Editor editor;

    public Wallet(){

    }

    public Wallet(double budget, double target, long startDate, long endDate,String name, String token) {
        this.budget = budget;
        this.target = target;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.token = token;
        int days = (int) ((endDate - startDate) / (1000*60*60*24));
        this.balance = budget/days;

        /*db = FirebaseDatabase.getInstance().getReference();
        db.child("Users").child(uid+"/wallet/"+token).child("budget").setValue(budget);
        db.child("Users").child(uid+"/wallet/"+token).child("target").setValue(target);
        db.child("Users").child(uid+"/wallet/"+token).child("name").setValue(name);
        db.child("Users").child(uid+"/wallet/"+token).child("date").setValue(date);*/

    }

    public void setContext(Context context){
        this.context = context;
        // set values in phone memory
        userStorage = context.getSharedPreferences("com.numus.budgee.UserStorage", Context.MODE_PRIVATE);
        editor = userStorage.edit();
        editor.putString("currentWallet",this.token);
        editor.commit();
    }

    public void deleteWallet(){
        userStorage = context.getSharedPreferences("com.numus.budgee.UserStorage", Context.MODE_PRIVATE);
        String uid = userStorage.getString("uid","empty data");
        db = FirebaseDatabase.getInstance().getReference();
        db.child("Users").child(uid+"/wallet/"+this.token).setValue(null);
    }

    public double getBudget() {
        return budget;
    }

    public double getTarget() {
        return target;
    }

    public long getDate() {
        return this.endDate;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public double getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setTarget(double target) {
        this.target = target;
    }
}
