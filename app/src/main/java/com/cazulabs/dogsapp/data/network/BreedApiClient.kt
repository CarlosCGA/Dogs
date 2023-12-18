package com.cazulabs.dogsapp.data.network

import com.cazulabs.dogsapp.core.DogAPIEndpoints
import com.cazulabs.dogsapp.data.model.BreedModel
import com.cazulabs.dogsapp.data.model.old.BreedResponse
import retrofit2.Response
import retrofit2.http.GET

interface BreedApiClient {

    @GET(DogAPIEndpoints.LIST_ALL_BREEDS)
    suspend fun getAllBreeds(): Response<BreedResponse>

}