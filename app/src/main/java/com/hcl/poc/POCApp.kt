package com.hcl.poc

import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.hcl.poc.interfaces.ListingInterface
import com.hcl.poc.presenter.ListingPresenter

/**
 * Created by akhilmalik on 07/02/18.
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

    fun getListingPresenter(listingInterface: ListingInterface): ListingPresenter {
        if (listingViewPresenter == null)
            listingViewPresenter = ListingPresenter(listingInterface)
        (listingViewPresenter as ListingPresenter).setCallbacks(listingInterface);
        return listingViewPresenter as ListingPresenter
    }

    companion object {

        @get:Synchronized
        var instance: POCApp? = null
            private set

        var listingViewPresenter: ListingPresenter? = null


    }
}