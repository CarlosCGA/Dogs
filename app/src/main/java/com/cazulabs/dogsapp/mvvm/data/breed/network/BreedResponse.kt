package com.cazulabs.dogsapp.mvvm.data.breed.network

import com.google.gson.annotations.SerializedName

data class BreedResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val breeds: Map<String, List<String>>
)