package com.example.nidhi.gopikago;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class NotificationMsg extends Service {
    boolean is_running;
    MediaPlayer mp;


    public NotificationMsg () {
        is_running = false;
    }

    // Called when the service is created

    public void onCreate() {


    }

    // Called as a result of a call to startService()


    public int onStartCommand (Intent intent, int flags, int startId) {

        //Assets.mp.stop();
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        Notify();






        // Get any extras in the intent
        Bundle extras = intent.getExtras();



        return START_STICKY;
    }

    private void Notify () {
        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_CANCEL_CURRENT
                );

        //Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("Dukkie D Duck")
                .setContentText("Dukkie is hungry")
                .setContentIntent(resultPendingIntent)
                .setSmallIcon(R.drawable.icon);

        // Create an ID number for this notification
        int mNotifyID = 0;
        // Get an instance of the notification manager
        NotificationManager manager = (NotificationManager) getSystemService (NOTIFICATION_SERVICE);
        // Issue the notification
        manager.notify(mNotifyID, mBuilder.build());
    }



    // Called when the service is being destroyed
    @Override
    public void onDestroy () {
        mp.stop();
        super.onDestroy();
        is_running = false;
        Toast.makeText (this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    // Not using this but it is required
    @Override
    public IBinder onBind (Intent intent) { return null;}
}

