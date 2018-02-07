package com.hcl.poc.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hcl.poc.R
import com.hcl.poc.model.FeedRow
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_feed.view.*

/**
 * Created by akhilmalik on 7/02/18.
 */
class ListingAdapter(val rows: List<FeedRow>, val activity: Activity) : RecyclerView.Adapter<ListingAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_feed, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return rows.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindItems(rows.get(position), position, activity)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(row: FeedRow, pos: Int, activity: Activity) {

            if (row.title == null)
                itemView.visibility = View.GONE
            else
                itemView.visibility = View.VISIBLE
            itemView.titleTV.text = row.title
            itemView.descTV.text = row.description

            if (row.image == null)
                itemView.imageView.visibility = View.GONE
            else {
                itemView.imageView.visibility = View.VISIBLE
                Picasso.with(activity).load(row.image).into(itemView.imageView); }
        }
    }

}