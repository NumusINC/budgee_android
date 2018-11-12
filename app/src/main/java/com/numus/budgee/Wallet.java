package com.numus.budgee;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Wallet {

    public double budget;
    public double target;
    public long date;
    public String name;
    public String token;
    public double balance;
    public Context context;

    private DatabaseReference db;
    SharedPreferences userStorage;
    SharedPreferences.Editor editor;


    public Wallet(double budget, double target, long date, String name, String token, Context context) {
        this.budget = budget;
        this.target = target;
        this.date = date;
        this.name = name;
        this.token = token;
        this.context = context;
        this.balance = 0;

        userStorage = context.getSharedPreferences("com.numus.budgee.UserStorage", Context.MODE_PRIVATE);
        String uid = userStorage.getString("uid","empty data");

        db = FirebaseDatabase.getInstance().getReference();
        db.child("Users").child(uid+"/wallet/"+token).child("budget").setValue(budget);
        db.child("Users").child(uid+"/wallet/"+token).child("target").setValue(target);
        db.child("Users").child(uid+"/wallet/"+token).child("name").setValue(name);
        db.child("Users").child(uid+"/wallet/"+token).child("date").setValue(date);

    }

    public double getBudget() {
        return budget;
    }

    public double getTarget() {
        return target;
    }

    public long getDate() {
        return date;
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
