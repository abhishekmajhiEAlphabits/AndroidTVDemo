package com.example.androidtvdemo

import android.app.KeyguardManager
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.os.Bundle
import android.os.PowerManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.leanback.app.BrowseSupportFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScreenTimerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScreenTimerFragment : BrowseSupportFragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("abhi", "onCreate")



    }
}