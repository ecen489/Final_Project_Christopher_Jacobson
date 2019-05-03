package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;

public class DailyBudget extends AppCompatActivity {
    private TextView tvDB;
    private ImageView ivFood;
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

    Float dailyBudget = 0.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_budget);
        dailyBudget = getIntent().getFloatExtra("dailyBudget",dailyBudget);
        dailyBudget = round(dailyBudget,2);
        ivFood = (ImageView)findViewById(R.id.ivFood);
        if (dailyBudget > 10.0f){
            ivFood.setImageResource(R.drawable.ribs);
        }else if (dailyBudget > 5.0f){
            ivFood.setImageResource(R.drawable.hotpocket);
        }else{
            ivFood.setImageResource(R.drawable.ramen);
        }
        tvDB = (TextView) findViewById(R.id.tvDB);
        String string = "You have a daily budget of $" + Float.toString(dailyBudget);
        tvDB.setText(string);
    }
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    private void addNotification() {

        // Builds your notification

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("You are broke")
                .setContentText("Get Money!");

        // Creates the intent needed to show the notification
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }



}


