package com.hcl.poc.presenter

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.hcl.poc.POCApp
import com.hcl.poc.interfaces.ListingInterface
import com.hcl.poc.util.Constants
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by akhilmalik on 07/02/18.
 */
class ListingPresenter {
    companion object {
        lateinit var listingInterface: ListingInterface
        lateinit var listingPresenter: ListingPresenter

        // For getting Singleton Instance
        fun getInstance(listInterface: ListingInterface): ListingPresenter {
            if (::listingPresenter.isInitialized) {
                listingInterface = listInterface
                return listingPresenter
            } else {
                listingPresenter = ListingPresenter()
                listingInterface = listInterface
                return listingPresenter
            }
        }
    }

    fun getFeed(){
        val stringRequest = object : StringRequest(Request.Method.GET, Constants.GET_FEED,
                Response.Listener<String> { response ->
                    try {
                        val obj = JSONObject(response)
                        parseResponse(obj)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                object : Response.ErrorListener {
                    override fun onErrorResponse(volleyError: VolleyError) {
                        listingInterface?.onError("Request Failed")
                    }
                }) {

        }
        //adding request to queue
        POCApp.instance?.addToRequestQueue(stringRequest)
    }

}