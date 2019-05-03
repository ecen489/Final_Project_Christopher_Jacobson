package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

public class CalendarActivity extends AppCompatActivity {
    Float balance = 0.0f;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        balance = getIntent().getFloatExtra("balance", balance);
        //email = getIntent().getStringExtra("email");
        //password = getIntent().getStringExtra("password");

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String date = (month+1) + "/" + dayOfMonth + "/" + year;
                Intent intent = new Intent(CalendarActivity.this, BudgetStart.class);
                intent.putExtra("date", date);
                intent.putExtra("balance",balance);
                intent.putExtra("year",year);
                intent.putExtra("month", month);
                intent.putExtra("dayOfMonth",dayOfMonth);
                //intent.putExtra("email",email);
                //intent.putExtra("password",password);

                startActivity(intent);

            }
        });
    }
}
