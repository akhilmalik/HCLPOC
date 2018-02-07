package com.hcl.poc.presenter

import android.app.Activity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.hcl.poc.POCApp
import com.hcl.poc.interfaces.ListingInterface
import com.hcl.poc.model.Feed
import com.hcl.poc.model.FeedRow
import com.hcl.poc.util.Constants
import org.json.JSONException

/**
 * Created by akhilmalik on 07/02/18.
 */
class ListingPresenter(var listingInterface: ListingInterface) {

    private var feed: Feed? = null

    // set call back for view
    fun setCallbacks(listInterface: ListingInterface) {
        listingInterface = listInterface
    }

    //Get feed
    fun getFeed(fromNetwork: Boolean = false, activity: Activity) {
        if (checkNetworkConnection(activity)) {
            if (feed == null || fromNetwork)
                getFeedFromNetwork()
            else
                listingInterface.onDataLoaded(feed!!)
        } else {
            listingInterface.onError("No Network Connection")
        }
    }

    // Check for network connectivity
    private fun checkNetworkConnection(activity: Activity): Boolean {

        return com.myandroidlib.network.connectivity.NetworkConnection.isNetworkAvailable(activity)

    }

    // Method to get all feeds via Volley
    fun getFeedFromNetwork() {
        val stringRequest = object : StringRequest(Request.Method.GET, Constants.GET_FEED,
                Response.Listener<String> { response ->
                    try {
                        parseResponse(response)
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

    private fun parseResponse(obj: String) {
        feed = Gson().fromJson(obj, Feed::class.java)
        feed!!.rows = filterFeed()
        listingInterface.onDataLoaded(feed as Feed)
    }

    private fun filterFeed(): List<FeedRow> {
        var filterFeed: ArrayList<FeedRow> = ArrayList<FeedRow>()
        for (row: FeedRow in feed!!.rows) {
            if (row.title != null)
                filterFeed.add(row)
        }
        return filterFeed
    }


}