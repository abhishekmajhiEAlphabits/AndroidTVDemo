package com.example.androidtvdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import android.util.Log

class WakeUpReceiver : BroadcastReceiver() {

    private lateinit var wakeLock: PowerManager.WakeLock
    private lateinit var powerManager: PowerManager;

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("abhi", "inside wakeup receiver")
//        if ((context.getSystemService(POWER_SERVICE) as PowerManager).isInteractive) {
//            Log.d("abhi", "inside wakeup receiver is interactive")
//        }else{
//            Log.d("abhi", "inside wakeup receiver NOT interactive")
////            Settings.System.putString(context.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, "0")
//        }


        // create an instance of Window class
        // and display the content on screen
//        val window = Window(context)
//        window.close()

        powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
                wakeLock = powerManager.newWakeLock(
                    PowerManager.FULL_WAKE_LOCK or
                            PowerManager.ACQUIRE_CAUSES_WAKEUP or
                            PowerManager.ON_AFTER_RELEASE, "appname::WakeLock"
                )


                wakeLock.acquire()
                wakeLock.release()
    }
}