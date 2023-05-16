package com.example.androidtvdemo;

import android.content.Context;
import android.os.PowerManager;

public class WakeOn {


//    public static void alarnOn(){
//        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE,"Screen OFF");
//
//    }

//    private lateinit var deviceManger: DevicePolicyManager
//    private lateinit var compName: ComponentName
//    private lateinit var newKeyguardLock: KeyguardManager.KeyguardLock




//    timeOutValue = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT)
//        deviceManger = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
//        compName = ComponentName(this, DeviceAdmin::class.java)
//        val km: KeyguardManager = getSystemService(KEYGUARD_SERVICE) as KeyguardManager
//        newKeyguardLock = km.newKeyguardLock("MainActivity");
//        powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
//        wakeLock = powerManager.newWakeLock(
//            PowerManager.PARTIAL_WAKE_LOCK or
////            PowerManager.FULL_WAKE_LOCK or
//                    PowerManager.ACQUIRE_CAUSES_WAKEUP,
////                    or
////                    PowerManager.ON_AFTER_RELEASE,
//                    "appname::ForceSleepWakelock"
//        )

//        val active: Boolean = deviceManger.isAdminActive(compName)

//        if (active) {
//            Log.d("TAG", "app already enabled")
//        } else {
//            enablePhone()
//        }


    //    fun enablePhone() {
//        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
//        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName)
//        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "You should enable the app!")
//        startActivityForResult(intent, RESULT_ENABLE)
//    }



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


//      if (android.os.Build.VERSION.SDK_INT >= 19) {
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

//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when (requestCode) {
//            RESULT_ENABLE -> {
//                if (resultCode == RESULT_OK) {
//                    Log.d(TAG, "App enabled in device admin")
//
//                } else {
//                    Toast.makeText(
//                            applicationContext, "Failed!",
//                            Toast.LENGTH_SHORT
//                    ).show()
//                }
//                return
//            }
//        }
//    }

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

    //        if (wakeLock.isHeld) {
//            Log.d("abhi", "release wakelock");
//            wakeLock.release();
//        }


//        timeOutValue = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT)

    //            Settings.System.putInt(
//                context.getContentResolver(),
//                Settings.System.SCREEN_OFF_TIMEOUT,
//                data
//            );

    //        i.putExtra("timeOut",timeOutValue)
    //            val data = intent.getIntExtra("timeOut",0)
}
