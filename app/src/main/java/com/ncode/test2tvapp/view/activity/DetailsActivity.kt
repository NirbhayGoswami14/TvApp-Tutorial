package com.ncode.test2tvapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.ncode.test2tvapp.R
import com.ncode.test2tvapp.view.fragment.DetailsFragment

class DetailsActivity :FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportFragmentManager.beginTransaction().replace(R.id.details_fragment,DetailsFragment()).commitNow()
    }
}