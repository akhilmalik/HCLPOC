package com.hcl.poc

import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * Created by akhilmalik on 04/01/18.
 */
class POCApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val requestQueue: RequestQueue? = null
        get() {
            if (field == null) {
                return Volley.newRequestQueue(applicationContext)
            }
            return field
        }

    fun <T> addToRequestQueue(request: Request<T>) {
        requestQueue?.add(request)
    }

    companion object {

        @get:Synchronized
        var instance: POCApp? = null
            private set

    }
}