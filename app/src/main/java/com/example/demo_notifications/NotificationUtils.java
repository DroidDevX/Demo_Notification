package com.example.demo_notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationUtils {
    private static String MY_CHANNEL_ID="myNotification";
    private static int MY_INTENT_ID=1;
    public static String ACTION_UPDATE_NOTIFICATION_STATUS="ACTION_UPDATE_NOTIFICATION_STATUS";

    private static PendingIntent createPendingIntent(Context context){
        /*Create Pending intents to start service, activity or some implicity intent like a camera app*/

        Intent intent = new Intent(context, MainActivity.class);
        intent.setAction("ACTION_UPDATE_NOTIFICATION_STATUS");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        return pendingIntent;

    }

    public static void createNotification(Context context){
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        //You have to create notfication channels for versions of Android OS greater or equal to Oreo
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationManager!=null) {
            NotificationChannel mChannel = new NotificationChannel(
                    MY_CHANNEL_ID, "Test Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);

        }




        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, MY_CHANNEL_ID)
                .setColor(Color.RED)
                .setSmallIcon(R.drawable.ic_notification_big)
                //.setLargeIcon(R.drawable.)
                .setContentTitle(" My notification title")
                .setContentText(" This is a notification")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        "Big text stytle-This is a notification")) //Looks for string in context text to apply style
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(createPendingIntent(context)) //Look at the first method on top (1)
                .setAutoCancel(true);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        notificationManager.notify(MY_INTENT_ID, notificationBuilder.build());

    }


}
