package com.numus.budgee;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.stats.WakeLockEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import java.util.Date;
import java.util.HashMap;

public class Expense {
    public String name;
    public double value;
    public long date;
    public String type;
    public String token;
    public boolean isIncome;
    public String walletKey;
    public Context context;
    private final static String TAG = "Testing";

    //Instance Database
    private DatabaseReference db;

    //Instance sotorage user
    SharedPreferences userStorage;
    SharedPreferences.Editor editor;

    public Expense(String name, final double value, long date, String type, String token, boolean isIncome) {
        this.name = name;
        this.value = value;
        this.date = date;
        this.type = type;
        this.token = token;
        this.isIncome = isIncome;

    }

    public void updateDataBase(){

        userStorage = context.getSharedPreferences("com.numus.budgee.UserStorage", Context.MODE_PRIVATE);
        String uid = userStorage.getString("uid","empty data");
        String currentWallet = userStorage.getString("currentWallet","no existe");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users/"+uid+"/wallet/"+currentWallet);
        //Log.i("Testing","REF: "+ref.toString());
        ref.runTransaction(new Transaction.Handler() {
            @NonNull @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {

                Log.i("Testing","REF :: " + mutableData);
                Wallet ex = mutableData.getValue(Wallet.class);

                Log.i("Testing","wallet :: " + ex);


                if (ex == null) {
                    return Transaction.success(mutableData);
                }

                if (isIncome){
                    ex.budget += value;
                    mutableData.setValue(ex);
                    return Transaction.success(mutableData);
                }else {
                    ex.budget -= value;
                    mutableData.setValue(ex);
                    return Transaction.success(mutableData);
                }



            }
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                Log.d("Testing", "postTransaction:onComplete:" + databaseError);
            }
        });

    }

    public void deleteExpense(){
        //userStorage = context.getSharedPreferences("com.numus.budgee.UserStorage", Context.MODE_PRIVATE);
        //String uid = userStorage.getString("uid","empty data");
        db = FirebaseDatabase.getInstance().getReference();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db.child("Users").child(uid+"/expense/"+this.token).setValue(null);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public long getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getToken() {
        return token;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
