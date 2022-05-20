package com.hossein.tamasha.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossein.tamasha.models.home.ResponseGenreList
import com.hossein.tamasha.models.home.ResponseMoviesList
import com.hossein.tamasha.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val moviesTopList = MutableLiveData<ResponseMoviesList>()
    val genresList = MutableLiveData<ResponseGenreList>()
    val lastMoviesList = MutableLiveData<ResponseMoviesList>()
    val loading = MutableLiveData<Boolean>()

    fun loadTopMoviesList(id: Int) = viewModelScope.launch {
        val respose = repository.topMoviesList(id)
        if (respose.isSuccessful) {
            moviesTopList.postValue(respose.body())
        }
    }

    fun loadGenersList() = viewModelScope.launch {
        val respose = repository.generList()
        if (respose.isSuccessful) {
            genresList.postValue(respose.body())
        }
    }

    fun loadLastMoviesList() = viewModelScope.launch {
        loading.postValue(true)
        val respose = repository.lastMovies()
        if (respose.isSuccessful) {
            lastMoviesList.postValue(respose.body())
        }
        loading.postValue(false)
    }

}