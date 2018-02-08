package com.hcl.poc.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.hcl.poc.R
import com.hcl.poc.util.Constants

/**
 * Created by akhilmalik on 07/02/18.
 * Splash screen for the app. Will be displayed for just 2 Sec
 */

class SplashActivity : AppCompatActivity() {

    private val handler = Handler()
    private val runnable = Runnable { launchMainActivity() }

    // Method for launching next activity
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
        handler.postDelayed(runnable, Constants.SPLASH_TIME)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}
