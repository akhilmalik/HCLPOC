package com.hcl.poc.views

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.hcl.poc.POCApp
import com.hcl.poc.R
import com.hcl.poc.adapter.ListingAdapter
import com.hcl.poc.interfaces.ListingInterface
import com.hcl.poc.model.Feed
import com.hcl.poc.model.FeedRow
import com.hcl.poc.presenter.ListingPresenter
import kotlinx.android.synthetic.main.activity_listing.*
import kotlinx.android.synthetic.main.content_listing.*

/**
 * Created by akhilmalik on 07/02/18.
 *
 * This class will show all the feeds in a Recycler View with pull to refresh functionality
 */
class ListingActivity : AppCompatActivity(), ListingInterface {


    private lateinit var listingPresenter: ListingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)

        setUpUI()
        setUpEvents()


    }


    // Method for initializing
    private fun setUpUI() {
        listingPresenter = POCApp.instance?.getListingPresenter(this) as ListingPresenter

        recyclerView.adapter = ListingAdapter(ArrayList<FeedRow>(), this)
        listingPresenter.getFeed(false, this)
    }

    // Method for setting events
    private fun setUpEvents() {
        // Floating button event
        fab.setOnClickListener { view ->
            refreshFeed()
        }

        // Swiperefreslayout refresh event
        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            refreshFeed()
        })
    }

    // Method for refreshing feeds from live server
    private fun refreshFeed() {
        swipeRefreshLayout.isRefreshing = true
        disableError()
        listingPresenter.getFeed(true, this)
    }


    override fun onResume() {
        super.onResume()
        disableError()
    }

    // Callback for successful data load
    override fun onDataLoaded(feed: Feed) {
        swipeRefreshLayout.isRefreshing = false
        supportActionBar?.title = feed.title
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerView.adapter = ListingAdapter(feed.rows, this)
    }

    // Callback for failure
    override fun onError(error: String) {
        swipeRefreshLayout.isRefreshing = false
        enableError()
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    // Enable error message
    private fun enableError() {
        errorTV.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    //disable error message
    private fun disableError() {
        errorTV.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }
}
