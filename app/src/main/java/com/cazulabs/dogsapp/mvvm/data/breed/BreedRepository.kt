package com.cazulabs.dogsapp.mvvm.data.breed

import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedModel
import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedProvider
import com.cazulabs.dogsapp.mvvm.data.breed.network.BreedService

class BreedRepository {

    private val api = BreedService()

    suspend fun getAllBreeds(): List<BreedModel> {
        val response = api.getBreeds()
        BreedProvider.breeds = response
        return response
    }

}