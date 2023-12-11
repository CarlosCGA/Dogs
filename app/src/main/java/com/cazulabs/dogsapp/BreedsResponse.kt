package com.cazulabs.dogsapp

import com.google.gson.annotations.SerializedName

data class BreedsResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val breeds: Map<String, List<String>>
)