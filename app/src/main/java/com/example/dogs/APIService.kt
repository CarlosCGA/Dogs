package com.example.dogs

import com.example.dogs.utils.DogAPIEndpoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET(DogAPIEndpoints.GET_ALL_IMAGES_BY_BREED)
    suspend fun getDogsByBreed(): Response<DogResponse>

    @GET(DogAPIEndpoints.LIST_ALL_BREEDS)
    suspend fun getAllBreeds(): Response<BreedsResponse>

    @GET(DogAPIEndpoints.GET_RANDOM_DOG_IMAGES_BY_BREED)
    suspend fun getRandomDogImagesByBreed(@Path("breed") breed: String, @Path("howMany") howMany: Int): Response<DogResponse>

    @GET(DogAPIEndpoints.GET_RANDOM_DOG_IMAGES_BY_BREED_AND_SUB_BREED)
    suspend fun getRandomDogImagesByBreedAndSubBreed(@Path("breed") breed: String, @Path("subBreed") subBreed: String, @Path("howMany") howMany: Int): Response<DogResponse>
}