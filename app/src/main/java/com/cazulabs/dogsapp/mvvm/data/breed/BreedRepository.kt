package com.cazulabs.dogsapp.mvvm.data.breed

import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedImageModel
import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedImageProvider
import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedModel
import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedProvider
import com.cazulabs.dogsapp.mvvm.data.breed.network.BreedService

class BreedRepository {

    private val api = BreedService()

    suspend fun getAllBreeds(): List<BreedModel> {
        val response = api.getAllBreeds()
        BreedProvider.breeds = response
        return response
    }

    suspend fun getBreedImage(breed: String, howMany: Int): List<BreedImageModel> {
        val response = api.getRandomBreedImages(breed, howMany)
        BreedImageProvider.images = response
        return response
    }

}