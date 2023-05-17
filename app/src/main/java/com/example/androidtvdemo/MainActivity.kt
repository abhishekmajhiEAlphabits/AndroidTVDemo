package com.example.androidtvdemo

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.*
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import java.util.*


/**
 * Loads [MainFragment].
 */
class MainActivity : FragmentActivity() {

    private lateinit var btnLock: Button
    private var isTimerSet = false

    var timeOutValue: String? = null
    private lateinit var wakeLock: PowerManager.WakeLock
    private lateinit var powerManager: PowerManager;
//    val RESULT_ENABLE = 1
//    private lateinit var relativeLayout: FrameLayout;

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null


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
        timeOutValue =
            Settings.System.getString(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT)
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


//        val commandToRun = "input keyevent 24"
//        try {
//            val process: Process = Runtime.getRuntime().exec(commandToRun)
////            process.waitFor()
//        }
//       catch (e:Exception){
//           Log.d("abhi","exception: $e")
//       }

        try {
            Settings.System.putString(contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, "0")
            isTimerSet = true
            Toast.makeText(
                applicationContext,
                "System will Turn Off after 10 seconds:$timeOutValue",
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

    override fun onPause() {
        super.onPause()
//        val isInteractive = (getSystemService(Context.POWER_SERVICE) as PowerManager).isInteractive


        if (isTimerSet) {

            //Here Make a Display over other apps screen which will show on 10sec which will again have a counter of 10sec.On load of that screen
            //give the  Settings.System.putString(contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, "0") code.

            Settings.System.putString(contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, "0")
        } else {
            Settings.System.putString(
                contentResolver,
                Settings.System.SCREEN_OFF_TIMEOUT,
                timeOutValue
            )
        }

    }

    override fun onResume() {
        super.onResume()
        if (isTimerSet) {
            Settings.System.putString(contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, "0")
        } else {
            Settings.System.putString(
                contentResolver,
                Settings.System.SCREEN_OFF_TIMEOUT,
                timeOutValue
            )
        }
    }

    fun turnScreenOff(context: Context) {


//        val policyManager = context
//            .getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
//        val adminReceiver = ComponentName(
//            context,
//            DeviceAdmin::class.java
//        )
//        val admin = policyManager.isAdminActive(adminReceiver)
//        if (admin) {
//            Log.i("abhi", "Going to sleep now.")
//            policyManager.lockNow()
//        } else {
//            Log.i("abhi", "Not an admin")
//            Toast.makeText(
//                context, "Not an admin",
//                Toast.LENGTH_LONG
//            ).show()
//        }
    }

    companion object {
        private var TAG = "ScreenTimer"
    }

    class MyBroadcastReceiver : BroadcastReceiver() {
        lateinit var powerManager: PowerManager
        lateinit var wakeLock: PowerManager.WakeLock
        override fun onReceive(context: Context, intent: Intent) {
            try {
//                Settings.System.putInt(
//                    context.getContentResolver(),
//                    Settings.System.SCREEN_OFF_TIMEOUT,
//                    1000 * 60 * 5
//                );

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

