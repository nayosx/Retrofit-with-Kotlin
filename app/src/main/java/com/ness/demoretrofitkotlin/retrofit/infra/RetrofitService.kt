package com.ness.demoretrofitkotlin.retrofit.infra

import com.ness.demoretrofitkotlin.retrofit.domain.MessageResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("todos/")
    fun getMessages(): Call<List<MessageResponse>>
}