package com.hcl.poc.interfaces

import com.hcl.poc.model.Feed

/**
 * Created by akhilmalik on 07/02/18.
 */
interface ListingInterface {
    // function to send data fetched from server to view
    fun onDataLoaded(feed: Feed)

    // call back for reporting error
    fun onError(error: String)
}