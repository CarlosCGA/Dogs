package com.cazulabs.dogsapp.data

import com.cazulabs.dogsapp.data.model.BreedModel
import com.cazulabs.dogsapp.data.model.BreedProvider
import com.cazulabs.dogsapp.data.network.BreedService

class BreedRepository {

    private val api = BreedService()

    suspend fun getAllBreeds(): List<BreedModel> {
        val response = api.getBreeds()
        BreedProvider.breeds = response
        return response
    }

}