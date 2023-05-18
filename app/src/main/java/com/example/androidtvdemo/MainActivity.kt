package com.example.androidtvdemo

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.*
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.util.Log
import android.view.KeyEvent
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

        try {
            checkOverlayPermission();
            checkWritePermission()
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Permissions not granted",
                Toast.LENGTH_SHORT
            ).show()
        }


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
//            Settings.System.putString(contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, "0")
            isTimerSet = true
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
            futureDate.add(Calendar.SECOND, 10)

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

        try {
            checkOverlayPermission();
            checkWritePermission()

            if (isTimerSet) {
                Log.d("abhi", "Timer set..onPause")
                //Here Make a Display over other apps screen which will show on 10sec which will again have a counter of 10sec.On load of that screen
                //give the  Settings.System.putString(contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, "0") code.
            } else {
                Settings.System.putString(
                    contentResolver,
                    Settings.System.SCREEN_OFF_TIMEOUT,
                    timeOutValue
                )
//            val intent = Intent(this,ForegroundService::class.java)
//            stopService(intent)
            }
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Permissions not granted",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun onResume() {
        super.onResume()

        try {
            checkOverlayPermission();
            checkWritePermission()

            if (isTimerSet) {
//            Settings.System.putString(contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, "0")
                Log.d("abhi", "Timer set..onResume")
            } else {
                Settings.System.putString(
                    contentResolver,
                    Settings.System.SCREEN_OFF_TIMEOUT,
                    timeOutValue
                )
            }
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Permissions not granted",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

//    fun startWindow() {
//        val window = Window(applicationContext)
//        window.open()
//    }

    // method for starting the service
    fun startService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // check if the user has already granted
            // the Draw over other apps permission
            if (Settings.canDrawOverlays(this)) {
                // start the service based on the android version
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(Intent(this, ForegroundService::class.java))
                } else {
                    startService(Intent(this, ForegroundService::class.java))
                }
            }
        } else {
            startService(Intent(this, ForegroundService::class.java))
        }
    }

    // method to ask user to grant the Overlay permission
    fun checkOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                // send user to the device settings
                val myIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                startActivity(myIntent)
            }
        }
    }

    // method to ask user to grant the Overlay permission
    fun checkWritePermission() {
        if (!Settings.System.canWrite(this)) {
            // send user to the device settings
            val myIntent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
            startActivity(myIntent)
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

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    // check if the user has already granted
                    // the Draw over other apps permission
                    if (Settings.canDrawOverlays(context)) {
                        // start the service based on the android version
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            context.startForegroundService(
                                Intent(
                                    context,
                                    ForegroundService::class.java
                                )
                            )
                        } else {
                            context.startService(Intent(context, ForegroundService::class.java))
                        }
                    }
                } else {
                    context.startService(Intent(context, ForegroundService::class.java))
                }

//                Settings.System.putInt(
//                    context.getContentResolver(),
//                    Settings.System.SCREEN_OFF_TIMEOUT,
//                    1000 * 60 * 5
//                );

//                powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
//                wakeLock = powerManager.newWakeLock(
//                    PowerManager.FULL_WAKE_LOCK or
//                            PowerManager.ACQUIRE_CAUSES_WAKEUP or
//                            PowerManager.ON_AFTER_RELEASE, "appname::WakeLock"
//                )
//
//
//                wakeLock.acquire()
//                wakeLock.release()
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

