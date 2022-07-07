package com.ncode.test2tvapp.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.ncode.test2tvapp.R
import com.ncode.test2tvapp.retrofit.ControllerInterFace
import com.ncode.test2tvapp.view.fragment.MainFragment

class MainActivity : FragmentActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.main_browse_fragment,MainFragment())
            .commitNow()
    }




}