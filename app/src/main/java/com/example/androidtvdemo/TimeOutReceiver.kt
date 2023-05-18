package com.example.androidtvdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import android.provider.Settings
import android.util.Log

class TimeOutReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
//        val window = ForegroundService()
//        window.windowObj.close()
        val value = intent.getStringExtra("timeOutValue")
        if ((context.getSystemService(Context.POWER_SERVICE) as PowerManager).isInteractive) {
            Log.d("abhi", "inside timeout receiver is interactive:$value")
            Settings.System.putString(
                context.contentResolver,
                Settings.System.SCREEN_OFF_TIMEOUT,
                value
            )
        } else {
            Log.d("abhi", "inside timeout receiver NOT interactive :$value")
            Settings.System.putString(
                context.contentResolver,
                Settings.System.SCREEN_OFF_TIMEOUT,
                value
            )
        }
    }
}