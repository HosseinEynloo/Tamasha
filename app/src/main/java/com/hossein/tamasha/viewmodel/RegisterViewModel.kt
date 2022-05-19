package com.hossein.tamasha.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossein.tamasha.models.register.BodyRegister
import com.hossein.tamasha.models.register.ResponseRegister
import com.hossein.tamasha.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository:RegisterRepository): ViewModel(){

    val loading=MutableLiveData<Boolean>()
    val registerUser=MutableLiveData<ResponseRegister>()

    fun sendRegisterInfo(body:BodyRegister)= viewModelScope.launch {
        loading.postValue(true)
        val response=repository.registerUser(body)
        if (response.isSuccessful){
            registerUser.postValue(response.body())
        }
        loading.postValue(false)
    }


}