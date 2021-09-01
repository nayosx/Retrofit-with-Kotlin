package com.ness.demoretrofitkotlin.retrofit.app

import com.ness.demoretrofitkotlin.retrofit.domain.MessageResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


class RetrofitInstance {

    companion object {

        private var BASE_URL = "https://jsonplaceholder.typicode.com/"
        private var retrofit: Retrofit? = null

        fun getInstance(): Retrofit? {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build()
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}