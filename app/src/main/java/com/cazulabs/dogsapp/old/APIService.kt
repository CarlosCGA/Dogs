package com.cazulabs.dogsapp.old

import com.cazulabs.dogsapp.mvvm.core.DogAPIEndpoints
import com.cazulabs.dogsapp.mvvm.data.dog.network.BreedImageResponse
import com.cazulabs.dogsapp.mvvm.data.breed.network.BreedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET(DogAPIEndpoints.GET_ALL_IMAGES_BY_BREED)
    suspend fun getDogsByBreed(@Path("breed") breed: String): Response<BreedImageResponse>

    @GET(DogAPIEndpoints.LIST_ALL_BREEDS)
    suspend fun getAllBreeds(): Response<BreedResponse>

    @GET(DogAPIEndpoints.GET_RANDOM_DOG_IMAGES_BY_BREED)
    suspend fun getRandomDogImagesByBreed(@Path("breed") breed: String, @Path("howMany") howMany: Int): Response<BreedImageResponse>

    @GET(DogAPIEndpoints.GET_RANDOM_DOG_IMAGES_BY_BREED_AND_SUB_BREED)
    suspend fun getRandomDogImagesByBreedAndSubBreed(@Path("breed") breed: String, @Path("subBreed") subBreed: String, @Path("howMany") howMany: Int): Response<BreedImageResponse>
}