package com.hossein.tamasha.api

import com.hossein.tamasha.models.register.BodyRegister
import com.hossein.tamasha.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun registerUser(@Body body:BodyRegister): Response<ResponseRegister>
}