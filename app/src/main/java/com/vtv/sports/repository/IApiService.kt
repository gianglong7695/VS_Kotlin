package com.vtv.sports.repository

import com.vtv.sports.model.detail.DetailRespone
import com.vtv.sports.model.menu.MenuRespone
import com.vtv.sports.model.news.NewsRespone
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

}