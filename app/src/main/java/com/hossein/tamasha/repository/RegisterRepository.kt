package com.hossein.tamasha.repository

import com.hossein.tamasha.api.ApiService
import com.hossein.tamasha.models.register.BodyRegister
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val api: ApiService) {

    suspend fun registerUser(body: BodyRegister) = api.registerUser(body)

}