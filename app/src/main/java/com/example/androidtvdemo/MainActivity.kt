package com.example.androidtvdemo

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import java.util.*


/**
 * Loads [MainFragment].
 */
class MainActivity : FragmentActivity() {

    private lateinit var btnLock: Button

//    var timeOutValue: Int? = null
//    private lateinit var wakeLock: PowerManager.WakeLock
//    private lateinit var powerManager: PowerManager;
//    val RESULT_ENABLE = 1
//    private lateinit var relativeLayout: FrameLayout;


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        relativeLayout = findViewById<FrameLayout>(R.id.main_browse_fragment)
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                .replace(R.id.main_browse_fragment, MainFragment())
//                .commitNow()
//        }
        btnLock = findViewById<Button>(R.id.btnLock)

//        if (ActivityCompat.checkSelfPermission(
//                applicationContext,
//                Manifest.permission.WRITE_SETTINGS
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            btnLock.isClickable = true
//        } else {
////            ActivityCompat.requestPermissions(
////                this,
////                arrayOf(Manifest.permission.WRITE_SETTINGS), 1234
////            )
//            Toast.makeText(
//                this,
//                "Modify System Settings permissions not granted",
//                Toast.LENGTH_SHORT
//            ).show()
//            btnLock.isClickable = false
//        }
    }


//    override fun onRequestPermissionsResult(
//        requestCode: Int, permissions: Array<String>, grantResults:
//        IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == 1234) {
//            btnLock.isClickable = true
//            Toast.makeText(
//                this,
//                "Permissions granted by the user.",
//                Toast.LENGTH_SHORT
//            ).show()
//        } else {
//            Toast.makeText(
//                this,
//                "Permissions not granted by the user.",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }

    @SuppressLint("InvalidWakeLockTag", "ShortAlarm")
    fun lockPhone(view: View?) {

        try {
            Settings.System.putInt(contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, 0);
            Toast.makeText(
                applicationContext,
                "System will Turn Off after 10 seconds",
                Toast.LENGTH_LONG
            ).show()

            val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val i = Intent(applicationContext, MyBroadcastReceiver::class.java)
            i.action = "com.example.androidtvdemo.START_ALARM"
            val pi = PendingIntent.getBroadcast(applicationContext, 0, i, 0);

            val futureDate: Calendar = Calendar.getInstance()
            futureDate.add(Calendar.SECOND, 30)

            am.setExact(AlarmManager.RTC_WAKEUP, futureDate.getTime().getTime(), pi);
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Modify System Settings permissions not granted",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        private var TAG = "ScreenTimer"
    }

    class MyBroadcastReceiver : BroadcastReceiver() {
        lateinit var powerManager: PowerManager
        lateinit var wakeLock: PowerManager.WakeLock
        override fun onReceive(context: Context, intent: Intent) {
            try {
                Settings.System.putInt(
                    context.getContentResolver(),
                    Settings.System.SCREEN_OFF_TIMEOUT,
                    1000 * 60 * 5
                );

                powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
                wakeLock = powerManager.newWakeLock(
                    PowerManager.FULL_WAKE_LOCK or
                            PowerManager.ACQUIRE_CAUSES_WAKEUP or
                            PowerManager.ON_AFTER_RELEASE, "appname::WakeLock"
                )


                wakeLock.acquire()
                wakeLock.release()
//                Toast.makeText(context, "System started", Toast.LENGTH_LONG)
//                    .show()
                Log.d("abhi", "screen wake up")

            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    "Modify System Settings permissions not granted",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}

