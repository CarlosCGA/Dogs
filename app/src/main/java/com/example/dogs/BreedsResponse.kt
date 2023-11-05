package com.example.dogs

import com.google.gson.annotations.SerializedName

data class BreedsResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val breeds: Map<String, List<String>>
)