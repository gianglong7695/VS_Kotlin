package com.vtv.sports.model.video
import com.google.gson.annotations.SerializedName
import com.vtv.sports.model.common.LastUpdated

/**
 * Created by Giang Long on 12/25/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
data class VideoRespone(
        @SerializedName("LastUpdated")
        val lastUpdated: LastUpdated,
        @SerializedName("VideoZone")
        val videoZone: List<VideoZone>,
        @SerializedName("HauTruong")
        val hauTruong: List<VideoBox>,
        @SerializedName("Highlight")
        val highlight: List<VideoBox>,
        @SerializedName("NoiBat")
        val noiBat: List<VideoBox>,
        @SerializedName("QuocTe")
        val quocTe: List<VideoBox>,
        @SerializedName("TongHopBanThang")
        val tongHopBanThang: List<VideoBox>,
        @SerializedName("TrongNuoc")
        val trongNuoc: List<VideoBox>,
        @SerializedName("TuVanTheThao")
        val tuVanTheThao: List<VideoBox>,
        @SerializedName("VtvSportLive")
        val vtvSportLive: List<VideoBox>,
        @SerializedName("VtvSportNews")
        val vtvSportNews: List<VideoBox>
)