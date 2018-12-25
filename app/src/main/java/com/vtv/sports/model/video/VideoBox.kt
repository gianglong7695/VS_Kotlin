package com.vtv.sports.model.video

import com.google.gson.annotations.SerializedName

/**
 * Created by Giang Long on 12/25/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
data class VideoBox(
        @SerializedName("Avatar")
        val avatar: String,
        @SerializedName("CommentUrl")
        val commentUrl: String,
        @SerializedName("Description")
        val description: String,
        @SerializedName("DistributionDate")
        val distributionDate: String,
        @SerializedName("Duration")
        val duration: String,
        @SerializedName("FileName")
        val fileName: String,
        @SerializedName("Id")
        val id: Int,
        @SerializedName("KeyVideo")
        val keyVideo: String,
        @SerializedName("Name")
        val name: String,
        @SerializedName("RowIndex")
        val rowIndex: String,
        @SerializedName("Tags")
        val tags: String,
        @SerializedName("UnsignName")
        val unsignName: String,
        @SerializedName("Url")
        val url: String,
        @SerializedName("Views")
        val views: Int,
        @SerializedName("ZoneId")
        val zoneId: Int,
        @SerializedName("ZoneName")
        val zoneName: String
)