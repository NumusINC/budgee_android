package com.numus.budgee;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddTransactionActivity extends AppCompatActivity {

    DataManager dataManager;
    String transactionType;
    Boolean flag_name = false;
    Boolean flag_qty = false;
    Boolean flag_type = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        dataManager = new DataManager(getApplicationContext());
    }

    public void press(View view){
        addItem();
        if(flag_name && flag_qty){
            if (flag_type){
                Intent intent = new Intent(getApplication(),DashboardActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(), "Missed transaction type!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addItem(){
        EditText et_name = findViewById(R.id.name_input);
        EditText et_qty  = findViewById(R.id.qty_input);
        Spinner spin_cat = findViewById(R.id.category_input);

        String name = "";
        Double qty = 0.0;

        if( TextUtils.isEmpty(et_name.getText())){
            et_name.setError( "Type the name!" );
            flag_name = false;
        }else{
            flag_name = true;
            name = et_name.getText().toString();
        }

        if( TextUtils.isEmpty(et_qty.getText())){
            et_qty.setError( "Type the quantity!" );
            flag_qty = false;
        }else{
            flag_qty = true;
            qty = Double.valueOf(et_qty.getText().toString());
        }

        String cat = spin_cat.getSelectedItem().toString();

        Transaction.Category category = Transaction.Category.DEFAULT;
        switch (cat){
            case "Groceries":
                category = Transaction.Category.GROCERIES;
                break;
            case "Payroll":
                category = Transaction.Category.PAYROLL;
                break;
            case "Health":
                category = Transaction.Category.HEALTH;
                break;
            case "Taxes":
                category = Transaction.Category.TAXES;
                break;
            case "Fun":
                category = Transaction.Category.FUN;
                break;
            case "Food":
                category = Transaction.Category.FOOD;
                break;
            case "Pet":
                category = Transaction.Category.PET;
                break;
        }
        if (flag_name&&flag_qty&&flag_type){
            dataManager.addTransaction(name, qty, category, transactionType);
        }
    }

    public void setTransactionType(View view){
        int itemID = view.getId();
        switch (itemID){
            case R.id.btn_expense:
                transactionType = "ex";
                findViewById(R.id.btn_expense).setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button_selected));
                findViewById(R.id.btn_income).setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button));
                flag_type = true;
                break;
            case R.id.btn_income:
                transactionType = "in";
                findViewById(R.id.btn_expense).setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button));
                findViewById(R.id.btn_income).setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button_selected));
                flag_type = true;
                break;
        }
    }

}
