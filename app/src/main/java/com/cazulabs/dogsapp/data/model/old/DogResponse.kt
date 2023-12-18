package com.cazulabs.dogsapp.data.model.old

import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val images: List<String>
)
