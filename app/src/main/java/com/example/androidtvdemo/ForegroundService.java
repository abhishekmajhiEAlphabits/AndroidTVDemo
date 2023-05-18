package com.example.androidtvdemo;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class ForegroundService extends Service {

    private Window window;

    public ForegroundService() {
    }


    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // create the custom or default notification
        // based on the android version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startMyOwnForeground();
        else
            startForeground(1, new Notification());


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // create an instance of Window class
        // and display the content on screen
        window = new Window(this);
        window.open();
//        try {
//            Toast.makeText(
//                    getApplicationContext(),
//                    "Please wait...",
//                    Toast.LENGTH_LONG
//            ).show();
//
//            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//            Intent i = new Intent(getApplicationContext(), ShutDownReceiver.class);
////            i.setAction() = "com.example.androidtvdemo.START_ALARM"
//            PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
//
//            Calendar futureDate = Calendar.getInstance();
//            futureDate.add(Calendar.SECOND, 10);
//
//            am.setExact(AlarmManager.RTC_WAKEUP, futureDate.getTime().getTime(), pi);
//        } catch (Exception e){
//            Toast.makeText(
//                    this,
//                    "Failed to start display popup",
//                    Toast.LENGTH_SHORT
//            ).show();
//        }
//        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    // for android version >=O we need to create
    // custom notification stating
    // foreground service is running
    @RequiresApi(Build.VERSION_CODES.O)
    private void startMyOwnForeground() {
        String NOTIFICATION_CHANNEL_ID = "example.permanence";
        String channelName = "Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_MIN);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle("Service running")
                .setContentText("Displaying over other apps")

                // this is important, otherwise the notification will show the way
                // you want i.e. it will show some default notification
                .setSmallIcon(R.drawable.app_icon_your_company)

                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }

    public Window getWindowObj(){
        return window;
    }
}
