package com.hossein.tamasha.repository

import com.hossein.tamasha.api.ApiService
import com.hossein.tamasha.models.home.ResponseMoviesList
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: ApiService) {

    suspend fun topMoviesList(id: Int) = api.moviesTopList(id)

    suspend fun generList() = api.generList()

    suspend fun lastMovies() = api.lastMoviesList()
}