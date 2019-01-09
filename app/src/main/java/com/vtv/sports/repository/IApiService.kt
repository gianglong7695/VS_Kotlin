package com.vtv.sports.repository

import com.vtv.sports.model.breakingnews.BreakingRespone
import com.vtv.sports.model.chart.ChartRespone
import com.vtv.sports.model.detail.DetailRespone
import com.vtv.sports.model.detail.LatestRespone
import com.vtv.sports.model.menu.MenuRespone
import com.vtv.sports.model.news.NewsRespone
import com.vtv.sports.model.score.ScoreRespone
import com.vtv.sports.model.video.VideoBox
import com.vtv.sports.model.video.VideoRespone
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Giang Long on 12/22/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
interface IApiService {

    @FormUrlEncoded
    @POST("menu-left/")
    fun getMenuLeft(
        @Field("secret_key") secretKey: String
    ): Call<MenuRespone>


    @FormUrlEncoded
    @POST("news/home/")
    fun getNewsHome(
        @Field("secret_key") secretKey: String
    ): Call<NewsRespone>


    @FormUrlEncoded
    @POST("news/zone/")
    fun getNewsZone(
        @Field("secret_key") secretKey: String,
        @Field("zone_id") zondId: String
    ): Call<NewsRespone>


    @FormUrlEncoded
    @POST("news/zone/paging/")
    fun getNewsZonePaging(
        @Field("secret_key") secretKey: String,
        @Field("zone_id") zondId: String,
        @Field("page_index") pageIndex: String
    ): Call<NewsRespone>


    @FormUrlEncoded
    @POST("news/detail/")
    fun getNewsDetail(
        @Field("secret_key") secretKey: String,
        @Field("news_id") newsId: String
    ): Call<DetailRespone>

    @FormUrlEncoded
    @POST("video/home/")
    fun getVideoHome(
        @Field("secret_key") secretKey: String
    ): Call<VideoRespone>

    @FormUrlEncoded
    @POST("video/zone/paging/")
    fun getVideoPaging(
        @Field("secret_key") secretKey: String,
        @Field("zone_id") zoneId: String,
        @Field("page_index") pageIndex: String,
        @Field("page_size") pageSize: String
    ): Call<VideoRespone>


    @FormUrlEncoded
    @POST("schedule/")
    fun getScore(
        @Field("secret_key") secretKey: String,
        @Field("id") id: String
    ): Call<ScoreRespone>


    @FormUrlEncoded
    @POST("standing/")
    fun getChart(
        @Field("secret_key") secretKey: String,
        @Field("id") id: String
    ): Call<ChartRespone>

    @FormUrlEncoded
    @POST("get-notify/")
    fun getBreakingNews(
        @Field("secret_key") secretKey: String
    ): Call<BreakingRespone>


    @FormUrlEncoded
    @POST("news/lastest/paging/")
    fun getLatestNews(
        @Field("secret_key") secretKey: String,
        @Field("page_index") page_index: String,
        @Field("page_size") page_size: String
    ): Call<LatestRespone>


}