package com.poc.pocappjava.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.poc.pocappjava.R;
import com.poc.pocappjava.adapters.ListingAdapter;
import com.poc.pocappjava.interfaces.ListingInterface;
import com.poc.pocappjava.model.Feed;
import com.poc.pocappjava.presenter.ListingPresenter;

/**
 * Created by akhilmalik on 08/02/18.
 */

public class ListingActivity extends AppCompatActivity implements ListingInterface {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ListingPresenter listingPresenter;
    TextView errorTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        listingPresenter = ListingPresenter.getInstance(this);
        setUpUI();
        setUpEvents();
    }

    private void setUpUI() {
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        errorTV = findViewById(R.id.errorTV);
        listingPresenter.getFeed(false, this);
    }

    private void setUpEvents() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFeed();
            }
        });
    }

    // Method for refreshing feeds from live server
    private void refreshFeed() {
        swipeRefreshLayout.setRefreshing(true);
        disableError();
        listingPresenter.getFeed(true, this);
    }
//

    @Override
    protected void onResume() {
        super.onResume();
        disableError();
    }

    // Enable error message
    private void enableError() {
        errorTV.setVisibility(View.VISIBLE);
        recyclerView.setVerticalScrollbarPosition(View.GONE);
    }

    //disable error message
    private void disableError() {
        errorTV.setVisibility(View.GONE);
        recyclerView.setVerticalScrollbarPosition(View.VISIBLE);
    }

    @Override
    public void onDataLoaded(Feed feed) {
        swipeRefreshLayout.setRefreshing(false);
        getSupportActionBar().setTitle(feed.getTitle());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        recyclerView.setAdapter(new ListingAdapter(feed.getRows(), this));
    }

    @Override
    public void onError(String error) {
        swipeRefreshLayout.setRefreshing(false);
        enableError();
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
