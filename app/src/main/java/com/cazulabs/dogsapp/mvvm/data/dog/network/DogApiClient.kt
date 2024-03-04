package com.cazulabs.dogsapp.mvvm.data.dog.network

import com.cazulabs.dogsapp.mvvm.core.DogAPIConstants
import com.cazulabs.dogsapp.mvvm.core.DogAPIEndpoints
import com.cazulabs.dogsapp.mvvm.data.dog.model.DogImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiClient {

    @GET(DogAPIEndpoints.GET_RANDOM_DOG_IMAGES_BY_BREED_AND_SUB_BREED)
    suspend fun getAllDogsByBreedAndSubBreed(
        @Path(DogAPIConstants.BREED) breed: String,
        @Path(DogAPIConstants.SUB_BREED) subBreed: String,
        @Path(DogAPIConstants.HOW_MANY) howMany: Int
    ): Response<DogImageResponse>

}