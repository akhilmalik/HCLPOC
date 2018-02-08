package com.poc.pocappjava.interfaces;

import com.poc.pocappjava.model.Feed;

/**
 * Created by akhilmalik on 08/02/18.
 */

public interface ListingInterface {

    // function to send data fetched from server to view
    void onDataLoaded(Feed feed);

    // call back for reporting error
    void onError(String error);
}
