package com.example.androidtvdemo

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import java.util.*

class ShutDownReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val defaultTimeOut =
            Settings.System.getString(context.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT)
//        Settings.System.putString(context.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, "0")
        Log.d("abhi", "inside shutdown receiver")

//        wake up Alarm manager
        try {
            Settings.System.putString(
                context.contentResolver,
                Settings.System.SCREEN_OFF_TIMEOUT,
                "0"
            )  //setting screen_timeout to 10sec
            val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val i = Intent(context, WakeUpReceiver::class.java)
//            i.action = "com.example.androidtvdemo.START_ALARM"
            val pi = PendingIntent.getBroadcast(context, 0, i, 0);

            val futureDate: Calendar = Calendar.getInstance()
            futureDate.add(Calendar.SECOND, 20)

            am.setExact(AlarmManager.RTC_WAKEUP, futureDate.getTime().getTime(), pi);
            Log.d("abhi", "inside wake up alarm manager")
        } catch (e: Exception) {
            Log.d("abhi", "wake up alarm manager failed")
        }

//        set default timeout
        try {

            val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val i = Intent(context, TimeOutReceiver::class.java)
            i.putExtra("timeOutValue",defaultTimeOut)
            val pi = PendingIntent.getBroadcast(context, 0, i, 0);

            val futureDate: Calendar = Calendar.getInstance()
            futureDate.add(Calendar.SECOND, 15)

            am.setExact(AlarmManager.RTC_WAKEUP, futureDate.getTime().getTime(), pi);
            Log.d("abhi", "inside set default timer alarm manager")
        } catch (e: Exception) {
            Log.d("abhi", "inside set default timer alarm manager failed")
        }
    }
}