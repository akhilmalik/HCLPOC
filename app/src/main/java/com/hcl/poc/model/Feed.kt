package com.hcl.poc.model

import com.google.gson.annotations.SerializedName

/**
 * Created by akhilmalik on 07/02/18.
 */
data class Feed(
        @SerializedName("title")
        val title: String,
        @SerializedName("rows")
        var rows: List<FeedRow>
)