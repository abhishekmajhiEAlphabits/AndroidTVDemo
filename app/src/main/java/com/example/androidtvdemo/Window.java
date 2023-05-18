package com.example.androidtvdemo;

import static android.content.Context.WINDOW_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Window {

    // declaring required variables
    private Context context;
    private View mView;
    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;
    private LayoutInflater layoutInflater;
    private AlarmManager am;
    private PendingIntent pi;

    public Window(Context context) {
        this.context = context;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // set the layout parameters of the window
            mParams = new WindowManager.LayoutParams(
                    // Shrink the window to wrap the content rather
                    // than filling the screen
                    WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT,
                    // Display it on top of other application windows
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    // Don't let it grab the input focus
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    // Make the underlying application window visible
                    // through any transparent parts
                    PixelFormat.TRANSLUCENT);

        }
        // getting a LayoutInflater
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // inflating the view with the custom layout we created
        mView = layoutInflater.inflate(R.layout.overlay_screen, null);
        // set onClickListener on the remove button, which removes
        // the view from the window
//        mView.findViewById(R.id.window_close).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                close();
//            }
//        });
        // Define the position of the
        // window within the screen
        mParams.gravity = Gravity.CENTER;
        mWindowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);


    }

    public void open() {

        try {
            // check if the view is already
            // inflated or present in the window
            if (mView.getWindowToken() == null) {
                if (mView.getParent() == null) {
                    mWindowManager.addView(mView, mParams);
                    try {
//            Settings.System.putString(contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, "0")
//                        Toast.makeText(
//                                context,
//                                "Please wait...",
//                                Toast.LENGTH_LONG
//                        ).show();

                        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                        Intent i = new Intent(context, ShutDownReceiver.class);
//            i.setAction() = "com.example.androidtvdemo.START_ALARM"
                        pi = PendingIntent.getBroadcast(context, 0, i, 0);

                        Calendar futureDate = Calendar.getInstance();
                        futureDate.add(Calendar.SECOND, 10);

                        am.setExact(AlarmManager.RTC_WAKEUP, futureDate.getTime().getTime(), pi);
                    } catch (Exception e) {
                        Toast.makeText(
                                context,
                                "Failed to start display popup",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
//                    Settings.System.putString(contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, "0")
                }
            }

            Timer timerObj = new Timer();
            TimerTask timerTaskObj = new TimerTask() {
                public void run() {
                    //perform your action here
                    close();
                }
            };
            timerObj.schedule(timerTaskObj, 0, 10);

        } catch (Exception e) {
            Log.d("Error1", e.toString());
        }

    }

    public void close() {

        try {
            // remove the view from the window
            ((WindowManager) context.getSystemService(WINDOW_SERVICE)).removeView(mView);
            // invalidate the view
            mView.invalidate();
            // remove all views
            ((ViewGroup) mView.getParent()).removeAllViews();

//            if (am != null && pi != null) {
//                am.cancel(pi);
//            }

            // the above steps are necessary when you are adding and removing
            // the view simultaneously, it might give some exceptions
        } catch (Exception e) {
            Log.d("Error2", e.toString());
        }
    }
}
