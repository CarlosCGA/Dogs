package com.cazulabs.dogsapp.mvvm.data.dog

import com.cazulabs.dogsapp.mvvm.data.dog.model.DogModel
import com.cazulabs.dogsapp.mvvm.data.dog.model.DogProvider
import com.cazulabs.dogsapp.mvvm.data.dog.network.DogService

class DogRepository {

    private val api = DogService()

    suspend fun getAllDogsByBreedAndSubBreed(breed: String, subBreed: String, howMany: Int): List<DogModel> {
        val response = api.getAllDogsByBreedAndSubBreed(breed, subBreed, howMany)
        DogProvider.dogs = response
        return response
    }

}