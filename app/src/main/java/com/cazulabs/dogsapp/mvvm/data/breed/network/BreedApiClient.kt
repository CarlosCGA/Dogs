package com.cazulabs.dogsapp.mvvm.data.breed.network

import com.cazulabs.dogsapp.mvvm.core.DogAPIEndpoints
import com.cazulabs.dogsapp.mvvm.core.DogAPIConstants
import com.cazulabs.dogsapp.mvvm.data.dog.network.BreedImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedApiClient {

    @GET(DogAPIEndpoints.LIST_ALL_BREEDS)
    suspend fun getAllBreeds(): Response<BreedResponse>

    @GET(DogAPIEndpoints.GET_RANDOM_DOG_IMAGES_BY_BREED)
    suspend fun getRandomBreedImages(
        @Path(DogAPIConstants.BREED) breed: String,
        @Path(DogAPIConstants.HOW_MANY) howMany: Int
    ): Response<BreedImageResponse>

}