package com.cazulabs.dogsapp.mvvm.data.breed.network

import com.cazulabs.dogsapp.mvvm.core.DogAPIEndpoints
import retrofit2.Response
import retrofit2.http.GET

interface BreedApiClient {

    @GET(DogAPIEndpoints.LIST_ALL_BREEDS)
    suspend fun getAllBreeds(): Response<BreedResponse>

}