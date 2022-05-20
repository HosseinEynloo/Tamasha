package com.hossein.tamasha.api

import com.hossein.tamasha.models.home.ResponseGenreList
import com.hossein.tamasha.models.home.ResponseMoviesList
import com.hossein.tamasha.models.register.BodyRegister
import com.hossein.tamasha.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("register")
    suspend fun registerUser(@Body body: BodyRegister): Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun moviesTopList(@Path("genre_id") id: Int): Response<ResponseMoviesList>

    @GET("genres")
    suspend fun generList(): Response<ResponseGenreList>


    @GET("movies")
    suspend fun lastMoviesList(): Response<ResponseMoviesList>

}