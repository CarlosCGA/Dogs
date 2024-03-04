package com.cazulabs.dogsapp.mvvm.data.dog.model

import com.cazulabs.dogsapp.mvvm.core.DogAPIConstants
import com.google.gson.annotations.SerializedName

data class DogImageResponse(
    @SerializedName(DogAPIConstants.STATUS) val status: String,
    @SerializedName(DogAPIConstants.MESSAGE) val images: List<String>
)
