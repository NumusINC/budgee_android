package com.numus.budgee;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class AddTransactionActivity extends AppCompatActivity {

    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        dataManager = new DataManager(getApplicationContext());
        FloatingActionButton myFab = findViewById(R.id.add);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),DashboardActivity.class);
                startActivity(intent);
                addItem();
            }
        });
    }

    public void addItem(){
        EditText et_name = findViewById(R.id.name_input);
        EditText et_qty  = findViewById(R.id.qty_input);
        Spinner spin_cat = findViewById(R.id.category_input);

        String name = et_name.getText().toString();
        Double qty = Double.valueOf(et_qty.getText().toString());
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
        }
        dataManager.addTransaction(name,qty,category);
    }

}
