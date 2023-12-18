package com.cazulabs.dogsapp.mvvm.data.dog.network

import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val images: List<String>
)
