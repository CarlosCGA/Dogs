package com.example.dogs

import com.example.dogs.utils.DogAPIEndpoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface APIService {
    @GET(DogAPIEndpoints.GET_ALL_IMAGES_OF_BREED)
    suspend fun getDogsByBreed(): Response<DogResponse>

    @GET(DogAPIEndpoints.LIST_ALL_BREEDS)
    suspend fun getAllBreeds(): Response<BreedsResponse>

    @GET(DogAPIEndpoints.GET_RANDOM_DOG_IMAGES_BY_BREED)
    suspend fun getRandomDogImagesByBreed(@Path("breed") breed: String, @Path("howMany") howMany: Int): Response<DogResponse>
}