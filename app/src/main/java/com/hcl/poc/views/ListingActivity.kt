package com.hcl.poc.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.hcl.poc.POCApp
import com.hcl.poc.R
import com.hcl.poc.adapter.ListingAdapter
import com.hcl.poc.interfaces.ListingInterface
import com.hcl.poc.model.Feed
import com.hcl.poc.presenter.ListingPresenter
import kotlinx.android.synthetic.main.activity_listing.*
import kotlinx.android.synthetic.main.content_listing.*

/**
 * Created by akhilmalik on 07/02/18.
 */
class ListingActivity : AppCompatActivity(), ListingInterface {


    lateinit var listingPresenter: ListingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)

        listingPresenter = POCApp.instance?.getListingPresenter(this) as ListingPresenter

        listingPresenter.getFeed(false, this)
        fab.setOnClickListener { view ->
            listingPresenter.getFeed(true, this)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDataLoaded(feed: Feed) {
        supportActionBar?.title = feed.title
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerView.adapter = ListingAdapter(feed.rows, this)
    }

    override fun onError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}
