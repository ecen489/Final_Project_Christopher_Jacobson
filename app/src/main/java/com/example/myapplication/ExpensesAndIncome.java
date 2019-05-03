package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

public class ExpensesAndIncome extends AppCompatActivity implements ListFrag.ExpenseListener, DetailFrag.ButtonListener{
    public Float expenses[] = {0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f};
    Integer previousIndex = 0;
    Integer numOfDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    Integer totalDays = 0;
    Integer totalMonths = 0;
    Float totalCost = 0.0f;
    Float balance = 0.0f;
    Float dailyBudget = 0.0f;
    Float newBalance = 0.0f;
    TextView tvDollar;
    EditText etCost;
    Button btSave;
    //String date = getIntent().getStringExtra("date");







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_and_income);
        balance = getIntent().getFloatExtra("balance",0.0f);
        Integer lastDay = getIntent().getIntExtra("lastDay", 0);
        Integer lastMonth = getIntent().getIntExtra("lastMonth", 0);
        Integer today = getIntent().getIntExtra("today", 0);
        Integer month = getIntent().getIntExtra("month", 0);
        etCost = (EditText) findViewById(R.id.etCost);
        tvDollar = (TextView) findViewById(R.id.tvDollar);
        btSave = (Button) findViewById(R.id.btSave);


        if(lastMonth > month){
            totalDays = (numOfDays[month] - today);
            totalDays = totalDays + lastDay;
        }else{
            totalDays = lastDay - today;
        }
        for (Integer i = month+1; i < lastMonth; i++){
            totalDays = totalDays + numOfDays[i];
        };

        if((today == 1) && (lastMonth == month)){
            totalMonths = 1;
        } else if((today == 1) && (lastMonth != month)){
            totalMonths = lastMonth - month + 1;
        } else if ((today > 1) && (lastMonth == month)){
            totalMonths = 0;
        }else{
            totalMonths = lastMonth - month;
        }

    }


    @Override
    public void onInputASent(boolean press) {
        if(press == true){
            expenses[previousIndex] = Float.parseFloat(etCost.getText().toString());
        }
    }


    @Override
    public void onExpenseSelected(int index) {
        expenses[previousIndex] = Float.parseFloat(etCost.getText().toString());
        etCost.setText(Float.toString(expenses[index]));
        previousIndex = index;
        if (index == 7){
            totalCost = totalMonths * (expenses[6] + expenses[5] + expenses[4] + expenses[3] + expenses[2] +
                    expenses[1] - expenses[0]);
            newBalance = balance - totalCost;
            dailyBudget = newBalance / totalDays;
            Intent intent = new Intent(ExpensesAndIncome.this, DailyBudget.class);
            intent.putExtra("dailyBudget",dailyBudget);
            startActivity(intent);
        }

    }



}
