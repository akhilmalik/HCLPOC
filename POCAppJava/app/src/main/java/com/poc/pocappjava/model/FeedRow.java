package com.poc.pocappjava.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akhilmalik on 08/02/18.
 */

public class FeedRow {
    @SerializedName("title")
    String title;
    @SerializedName("description")
    String description;
    @SerializedName("imageHref")
    String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
