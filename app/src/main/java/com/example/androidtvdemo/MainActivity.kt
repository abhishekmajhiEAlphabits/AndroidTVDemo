package com.example.androidtvdemo

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PowerManager
import android.os.SystemClock
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.FragmentActivity
import java.util.*


/**
 * Loads [MainFragment].
 */
class MainActivity : FragmentActivity() {

    private lateinit var btnLock: Button
//    private lateinit var deviceManger: DevicePolicyManager
//    private lateinit var compName: ComponentName
//    private lateinit var newKeyguardLock: KeyguardManager.KeyguardLock

    private lateinit var wakeLock: PowerManager.WakeLock
    private lateinit var powerManager: PowerManager;

    val RESULT_ENABLE = 1
    private lateinit var relativeLayout: FrameLayout;


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        relativeLayout = findViewById<FrameLayout>(R.id.main_browse_fragment)
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                .replace(R.id.main_browse_fragment, MainFragment())
//                .commitNow()
//        }


        btnLock = findViewById<Button>(R.id.btnLock)


//        deviceManger = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
//        compName = ComponentName(this, DeviceAdmin::class.java)
//        val km: KeyguardManager = getSystemService(KEYGUARD_SERVICE) as KeyguardManager
//        newKeyguardLock = km.newKeyguardLock("MainActivity");
//        powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
//        wakeLock = powerManager.newWakeLock(
//            PowerManager.FULL_WAKE_LOCK or
//                    PowerManager.ACQUIRE_CAUSES_WAKEUP or
//                    PowerManager.ON_AFTER_RELEASE, "appname::WakeLock"
//        )

//        val active: Boolean = deviceManger.isAdminActive(compName)

//        if (active) {
//            Log.d("TAG", "app already enabled")
//        } else {
//            enablePhone()
//        }
    }

//    fun enablePhone() {
//        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
//        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName)
//        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "You should enable the app!")
//        startActivityForResult(intent, RESULT_ENABLE)
//    }

    @SuppressLint("InvalidWakeLockTag", "ShortAlarm")
    fun lockPhone(view: View?) {
//        if (wakeLock.isHeld) {
//            Log.d("abhi", "release wakelock");
//            wakeLock.release();
//        }

        Settings.System.putInt(contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, 0);
        Toast.makeText(
            applicationContext,
            "System will Turn Off after 10 seconds",
            Toast.LENGTH_LONG
        ).show()

//
        val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val i = Intent(applicationContext, MyBroadcastReceiver::class.java)
        i.action = "com.example.androidtvdemo.START_ALARM"
        val pi = PendingIntent.getBroadcast(applicationContext, 0, i, 0);
////                am.set(
////                    AlarmManager.RTC_WAKEUP,
////                    System.currentTimeMillis(), pi
////                )
//
//        val alarmClockInfo = AlarmManager.AlarmClockInfo(25000, pi)
//        am.setAlarmClock(alarmClockInfo,pi)
//        am.setRepeating(
//            AlarmManager.ELAPSED_REALTIME_WAKEUP,
//            SystemClock.elapsedRealtime(),
//            10000*100, pi
//        )// Millisec * Second * Minute

        val futureDate: Calendar = Calendar.getInstance()
        futureDate.add(Calendar.SECOND, 30)

        am.setExact(AlarmManager.RTC_WAKEUP, futureDate.getTime().getTime(), pi);

//        if (android.os.Build.VERSION.SDK_INT >= 19) {
//            am.setExact(AlarmManager.RTC_WAKEUP, futureDate.getTime().getTime(), pi);
//        }
//        else {
//           am.set(AlarmManager.RTC_WAKEUP, time, pIntent);
//        }


//        btnLock.isClickable = false
//        Handler().postDelayed(Runnable {
//            kotlin.run {
//                Log.d("abhi", "inside on")
////                Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, Integer.MAX_VALUE);
//                wakeLock = powerManager.newWakeLock(
//                    PowerManager.FULL_WAKE_LOCK or
//                            PowerManager.ACQUIRE_CAUSES_WAKEUP or
//                            PowerManager.ON_AFTER_RELEASE, "appname::WakeLock"
//                )
//                //acquire will turn on the display
//                wakeLock.acquire()
////        release will release the lock from CPU, in case of that, screen will go back to sleep mode in defined time bt device settings
//                wakeLock.release()
//            }
//        }, 25000)

    }

//        val i = Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN")
//        i.putExtra("android.intent.extra.KEY_CONFIRM", true)
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(i)


//        val powerMgr = getSystemService(POWER_SERVICE) as PowerManager
//        powerMgr.
//        Toast.makeText(applicationContext,"hi",Toast.LENGTH_LONG).show()
//        Thread(Runnable { kotlin.run {
//            onOff()
//        } }).start()
//        onOff()
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
//            val v = this.window.decorView
//            v.systemUiVisibility = View.GONE
//        } else if (Build.VERSION.SDK_INT >= 19) {
//            //for new api versions.
//            val decorView = window.decorView
//            val uiOptions =
//                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//            decorView.systemUiVisibility = uiOptions
//        }
//        relativeLayout.setBackgroundColor(Color.parseColor("#000000"))
//        findViewById<TextView>(R.id.id1).visibility = View.GONE
//        btnLock.visibility = View.GONE
//        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
//        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
//        audioManager.setStreamVolume(AudioManager.STREAM_ALARM, 0, 0);
//        audioManager.setStreamVolume(AudioManager.STREAM_RING, 0, 0);

//        Settings.System.putInt(applicationContext.contentResolver,
//            Settings.System.SCREEN_BRIGHTNESS, 1);
//        relativeLayout.keepScreenOn = false
//        val screenLock = (getSystemService(POWER_SERVICE) as PowerManager).newWakeLock(
//            PowerManager.SCREEN_BRIGHT_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG"
//        )
//        screenLock.acquire()

//        val params = this.window.attributes
//        /** Turn off: */
//        /** Turn off:  */
//        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//        //TODO Store original brightness value
//        params.screenBrightness = 0f
//        this.window.attributes = params


//    private fun onOff() {
//        val active = deviceManger.isAdminActive(compName)
//        if (active) {
//
//            deviceManger.lockNow()
//
//            Handler().postDelayed(Runnable {
//                kotlin.run {
//                    newKeyguardLock.disableKeyguard();
//
//                    //acquire will turn on the display
//                    wakeLock.acquire()
//
//                    //release will release the lock from CPU, in case of that, screen will go back to sleep mode in defined time bt device settings
//                    wakeLock.release()
//                }
//            }, 30000)
//
////            Thread.sleep(30000)
//
//        } else {
//            enablePhone()
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RESULT_ENABLE -> {
                if (resultCode == RESULT_OK) {
//                    btnEnable.text = "Disable"
//                    btnLock.visibility = View.VISIBLE
                    Log.d(TAG, "App enabled in device admin")

                } else {
                    Toast.makeText(
                        applicationContext, "Failed!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    companion object {
        private var TAG = "ScreenTimer"
    }

    class MyBroadcastReceiver : BroadcastReceiver() {
        lateinit var powerManager: PowerManager
        lateinit var wakeLock: PowerManager.WakeLock
        override fun onReceive(context: Context, intent: Intent) {
//            Settings.System.putInt(
//                context.getContentResolver(),
//                Settings.System.SCREEN_OFF_TIMEOUT,
//                1000 * 60 * 5
//            );
//            if (intent.action.equals("com.example.androidtvdemo.START_ALARM")) {
            powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
            wakeLock = powerManager.newWakeLock(
                PowerManager.FULL_WAKE_LOCK or
                        PowerManager.ACQUIRE_CAUSES_WAKEUP or
                        PowerManager.ON_AFTER_RELEASE, "appname::WakeLock"
            )
//                if (wakeLock.isHeld) {
//                    Log.d("abhi", "release wakelock");
//                    wakeLock.release();
//                }

            wakeLock.acquire()
//            wakeLock.release()
//                Toast.makeText(context, "System started", Toast.LENGTH_LONG)
//                    .show()
            Log.d("abhi", "screen wake up")
//            }

        }
    }
}

