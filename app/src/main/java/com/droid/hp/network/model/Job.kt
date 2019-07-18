package com.droid.hp.network.model


import com.google.gson.annotations.SerializedName


data class Job(
        @SerializedName("jobs")
        val jobs: List<JobsItem> = listOf()) {
    data class JobsItem(
            @SerializedName("jobId")
            val jobId: Int = 0,
            @SerializedName("connectedBusinesses")
            val connectedBusinesses: List<ConnectedBusinessesItem>?,
            @SerializedName("detailsLink")
            val detailsLink: String = "",
            @SerializedName("category")
            val category: String = "",
            @SerializedName("postedDate")
            val postedDate: String = "",
            @SerializedName("status")
            val status: String = "") {
        data class ConnectedBusinessesItem(
                @SerializedName("thumbnail")
                val thumbnail: String = "",
                @SerializedName("isHired")
                val isHired: Boolean = false,
                @SerializedName("businessId")
                val businessId: Int = 0)
    }
}


