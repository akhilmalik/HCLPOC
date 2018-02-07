package com.hcl.poc.model

import com.google.gson.annotations.SerializedName

/**
 * Created by akhilmalik on 07/02/18.
 */
data class FeedRow(
        @SerializedName("title")
        val title: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("imageHref")
        val image: String

) {
}