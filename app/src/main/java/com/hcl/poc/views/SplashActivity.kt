package com.hcl.poc.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.hcl.poc.R

/**
 * Created by akhilmalik on 07/02/18.
 */

class SplashActivity : AppCompatActivity() {

    val handler = Handler()
    var runnable = Runnable { launchMainActivity() }

    private fun launchMainActivity() {
        val intent = Intent(this, ListingActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}
