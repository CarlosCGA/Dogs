package com.cazulabs.dogsapp.core

import com.cazulabs.dogsapp.data.model.BreedModel
import com.cazulabs.dogsapp.data.model.old.BreedResponse

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