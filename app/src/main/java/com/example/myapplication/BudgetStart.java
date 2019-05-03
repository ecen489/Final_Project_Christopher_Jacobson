package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.Calendar;



public class BudgetStart extends AppCompatActivity {

    private EditText avaliableBalance;
    private TextView lastDayOfSchool;
    private Button start;
    private ImageButton cal;
    private TextView avaBal;
    private TextView daysLeft;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseAuth firebaseAuth;
    //FirebaseUser user = null;
    String email;
    String password;

    float balance = 0.0f;
    Integer numOfDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    Integer totalDays = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);
        avaliableBalance = (EditText)findViewById(R.id.etAB);
        lastDayOfSchool = (TextView)findViewById(R.id.tvLDoS);
        start = (Button)findViewById(R.id.btnStart);
        cal = (ImageButton)findViewById(R.id.iBCal);
        avaBal = (TextView)findViewById(R.id.tvAvaBal);
        daysLeft = (TextView)findViewById(R.id.tvDaysLeft);
        String date = getIntent().getStringExtra("date");
        balance = getIntent().getFloatExtra("balance",0.0f);
        final Integer lastDay = getIntent().getIntExtra("dayOfMonth", 0);
        final Integer lastMonth = getIntent().getIntExtra("month", 0);
        Calendar calendar = Calendar.getInstance();
        //int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int today = calendar.get(Calendar.DAY_OF_MONTH);

        /*
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        firebaseAuth = FirebaseAuth.getInstance();

        int iend = email.indexOf(".");
        String emailKey;
        emailKey = email.substring(0,iend);

        DatabaseReference userKey = myRef.child("database/users/");
        Query query = userKey.orderByChild("email").equalTo(emailKey);
        query.addListenerForSingleValueEvent(valueEventListener);

        */

        if(balance!=0.0f){
            //String sBalance = Float.toString(balance);
            avaliableBalance.setText(""+balance);
        }

        if(date!=null) {
            start.setClickable(true);
            if(lastMonth > month){
                totalDays = (numOfDays[month] - today);
                totalDays = totalDays + lastDay;
            }else{
                totalDays = lastDay - today;
            }
            for (Integer i = month+1; i < lastMonth; i++){
                totalDays = totalDays + numOfDays[i];
            };
            String daysLeftString =  Integer.toString(totalDays) + " Days Left";
            daysLeft.setText(daysLeftString);
            //daysLeft.setText(today + "/" + (month + 1);
            String newDate = "Last Day of School: " + date;
            lastDayOfSchool.setText(newDate);
        }

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalDays = 0;
                float balance = Float.parseFloat(avaliableBalance.getText().toString());
                Intent intent = new Intent(BudgetStart.this, CalendarActivity.class);
                if(balance!=0.0f){
                    intent.putExtra("balance",balance);
                }else{
                    balance = 0.0f;
                    intent.putExtra("balance",balance);}
                startActivity(intent);

            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sintent = new Intent(BudgetStart.this, ExpensesAndIncome.class);


                sintent.putExtra("balance", balance);

                sintent.putExtra("month",month);
                sintent.putExtra("today",today);
                sintent.putExtra("lastMonth",lastMonth);
                sintent.putExtra("lastDay",lastDay);
                //sintent.putExtra("email",email);
                //sintent.putExtra("password",password);

                startActivity(sintent);
            }
        });

    }
    /*
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                //Toast.makeText(getApplicationContext(),"listening",Toast.LENGTH_SHORT).show();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);

                    Integer lastDay = user.getday();
                    Integer lastMonth = user.getmonth();
                    lastDayOfSchool.setText("IT WORKED");



                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //log error

        }
    };
    */
}
