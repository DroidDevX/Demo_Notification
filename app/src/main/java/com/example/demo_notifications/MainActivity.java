package com.example.demo_notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static String TAG ="MainActivity";
    TextView notficationStatusTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notficationStatusTV = findViewById(R.id.statusTV);

        if(getIntent().getAction()!=null)
            if(getIntent().getAction().equals(NotificationUtils.ACTION_UPDATE_NOTIFICATION_STATUS))
                updateStatusTV("Updated from notification");
    }

    public void createNotificationBtnClicked(View view) {
        NotificationUtils.createNotification(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG,"onNewIntent");
        Log.e(TAG,"Intent Action -"+intent.getAction());


    }

    public void updateStatusTV(String message){
        if(notficationStatusTV!=null)
            notficationStatusTV.setText(String.format("Status: %s",message));
    }
}
