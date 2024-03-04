package com.cazulabs.dogsapp.mvvm.data.dog.network

import com.cazulabs.dogsapp.mvvm.core.DogAPIConstants
import com.google.gson.annotations.SerializedName

data class BreedImageResponse(
    @SerializedName(DogAPIConstants.STATUS) val status: String,
    @SerializedName(DogAPIConstants.MESSAGE) val images: List<String>
)
