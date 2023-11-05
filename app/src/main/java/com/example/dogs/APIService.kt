package com.example.dogs

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getDogsByBreed(@Url url: String): Response<DogResponse>

    @GET
    suspend fun getAllBreeds(@Url url: String): Response<BreedsResponse>
}