package com.cazulabs.dogsapp.mvvm.core

import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedModel
import com.cazulabs.dogsapp.mvvm.data.breed.network.BreedResponse

class BreedHelper {

    fun parseBreedResponseToBreedModel(breedResponse: BreedResponse): List<BreedModel> {
        val breeds = mutableListOf<BreedModel>()
        if (breedResponse.breeds.isNotEmpty()) {
            breedResponse.breeds.keys.forEach { breed ->
                breeds.add(
                    BreedModel(
                        breed,
                        "",
                        breedResponse.breeds[breed] ?: emptyList()
                    )
                )
            }
        }
        return breeds
    }

}