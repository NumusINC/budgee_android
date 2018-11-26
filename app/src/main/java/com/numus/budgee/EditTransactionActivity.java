package com.numus.budgee;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class EditTransactionActivity extends AppCompatActivity {

    String pos_str;
    DataManager dataManager;
    String transactionType;
    Boolean flag_name = false;
    Boolean flag_qty = false;
    Boolean flag_type = false;
    int position;
    EditText et_name,et_qty;
    Spinner spin_cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transaction);

        dataManager = new DataManager(getApplicationContext());

        et_name = findViewById(R.id.name_get);
        et_qty  = findViewById(R.id.qty_get);
        spin_cat = findViewById(R.id.category_input);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt("position");
        }

        Transaction transaction = dataManager.getTransactionArray().get(position);

        LinearLayout cl = findViewById(R.id.editLay);

        Transaction.Category category = transaction.getCategory();

        switch (category){
            case PET:
                cl.setBackgroundColor(Color.parseColor("#20CC97"));
                break;
            case PAYROLL:
                cl.setBackgroundColor(Color.parseColor("#4e94ff"));
                break;
            case GROCERIES:
                cl.setBackgroundColor(Color.parseColor("#ef4c57"));
                break;
            case TAXES:
                cl.setBackgroundColor(Color.parseColor("#39374A"));
                break;
            case FOOD:
                cl.setBackgroundColor(Color.parseColor("#FFA917"));
                break;
            case FUN:
                cl.setBackgroundColor(Color.parseColor("#FFD103"));
                break;
            case SERVICES:
                cl.setBackgroundColor(Color.parseColor("#CE9A78"));
                break;
            case HEALTH:
                cl.setBackgroundColor(Color.parseColor("#B558F4"));
                break;
        }



        et_name.setText(transaction.getName());
        et_qty.setText(transaction.getQuantity().toString());
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

        Transaction transaction = dataManager.getTransactionArray().get(position);

        Transaction.Category category = transaction.getCategory();
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
            dataManager.editTransaction(name, qty, category, transactionType,position);
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
