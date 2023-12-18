package com.cazulabs.dogsapp.data.model.old

import com.google.gson.annotations.SerializedName

data class BreedResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val breeds: Map<String, List<String>>
)