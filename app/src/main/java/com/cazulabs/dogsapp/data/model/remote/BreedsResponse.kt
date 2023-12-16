package com.cazulabs.dogsapp.data.model.remote

import com.google.gson.annotations.SerializedName

data class BreedsResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val breeds: Map<String, List<String>>
)