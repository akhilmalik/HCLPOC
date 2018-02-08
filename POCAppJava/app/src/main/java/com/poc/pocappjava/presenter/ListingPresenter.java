package com.poc.pocappjava.presenter;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.poc.pocappjava.POCApp;
import com.poc.pocappjava.interfaces.ListingInterface;
import com.poc.pocappjava.model.Feed;
import com.poc.pocappjava.model.FeedRow;
import com.poc.pocappjava.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akhilmalik on 08/02/18.
 */

public class ListingPresenter {

    private Feed feed;
    ListingInterface listingInterface;

    static ListingPresenter listingPresenter;

    public static ListingPresenter getInstance(ListingInterface listingInterface){
        if(listingPresenter==null)
            listingPresenter = new ListingPresenter();
        listingPresenter.setCallbacks(listingInterface);
        return listingPresenter;
    }

    // set call back for view
    void setCallbacks(ListingInterface listInterface) {
        listingInterface = listInterface;
    }

    //Get feed
    public void getFeed(boolean fromNetwork, Activity activity) {
        if (checkNetworkConnection(activity)) {
            if (feed == null || fromNetwork)
                getFeedFromNetwork();
            else
                listingInterface.onDataLoaded(feed);
        } else {
            listingInterface.onError("No Network Connection");
        }
    }

    // Check for network connectivity
    private boolean checkNetworkConnection(Activity activity) {

        return com.myandroidlib.network.connectivity.NetworkConnection.isNetworkAvailable(activity);

    }

    // Method to get all feeds via Volley
    void getFeedFromNetwork() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.GET_FEED, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listingInterface.onError("Request Failed");
            }
        });

        //adding request to queue
        POCApp.getInstance().addToRequestQueue(stringRequest);
    }

    // Parsing JSON response from GSON
    private void parseResponse(String obj) {
        feed = new Gson().fromJson(obj, Feed.class);
        feed.setRows(filterFeed());
        listingInterface.onDataLoaded(feed);
    }

    // Filtering the results and getting rid of any null values
    private ArrayList<FeedRow> filterFeed() {
        ArrayList<FeedRow> feeds = new ArrayList<FeedRow>();
        for (FeedRow row : feed.getRows()) {
            if (row.getTitle() != null)
                feeds.add(row);
        }
        return feeds;
    }


}
