package com.hcl.hclpoc.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.hcl.hclpoc.R

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
