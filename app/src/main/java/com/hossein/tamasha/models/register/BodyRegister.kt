package com.hossein.tamasha.models.register

import com.google.gson.annotations.SerializedName

data class BodyRegister(
    @SerializedName("email")
    var email: String? = "",
    @SerializedName("password")
    var password: String? = "",
    @SerializedName("name")
    var name: String? = "",
)
