package com.poc.pocappjava.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akhilmalik on 08/02/18.
 */

public class Feed {
    @SerializedName("title")
    String title;
    @SerializedName("rows")
    ArrayList<FeedRow> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<FeedRow> getRows() {
        return rows;
    }

    public void setRows(ArrayList<FeedRow> rows) {
        this.rows = rows;
    }
}
