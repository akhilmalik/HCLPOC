package com.poc.pocappjava.adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.poc.pocappjava.R;
import com.poc.pocappjava.model.FeedRow;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by akhilmalik on 08/02/18.
 */

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.MyViewHolder> {

    private ArrayList<FeedRow> feeds;
    FragmentActivity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, descTv;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.titleTV);
            descTv = (TextView) view.findViewById(R.id.descTV);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }


    public ListingAdapter(ArrayList<FeedRow> feeds, FragmentActivity mActivity) {
        this.feeds = feeds;
        this.mActivity = mActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mActivity)
                .inflate(R.layout.item_feed, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        try {
            holder.title.setText(feeds.get(position).getTitle());
            holder.descTv.setText(feeds.get(position).getDescription());

            Picasso.with(mActivity).load(feeds.get(position).getImage()).into(holder.imageView, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    holder.imageView.setVisibility(View.GONE);
                }
            });

        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

}
