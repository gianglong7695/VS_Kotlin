package com.vtv.sports.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Giang Long on 12/22/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class BaseService {
    companion object {
        private val timeOut = 60
        private var retrofit: Retrofit? = null

        fun getService(): IApiService {
            return getRetrofit(ApiConstant.SERVER_DOMIAN)!!.create(IApiService::class.java)
        }


        private fun getRetrofit(domain: String): Retrofit? {
            if (retrofit == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder().apply {
                    readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
                    writeTimeout(timeOut.toLong(), TimeUnit.SECONDS)
                    connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
                    addInterceptor(interceptor)
                    addInterceptor { chain ->
                        var request = chain.request()
                        request = request.newBuilder()
                            .build()
                        val response = chain.proceed(request)
                        response
                    }
                }

                retrofit = Retrofit.Builder()
                    .baseUrl(domain)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }

}