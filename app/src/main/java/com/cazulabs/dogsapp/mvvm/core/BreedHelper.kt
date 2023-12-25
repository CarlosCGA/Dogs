package com.cazulabs.dogsapp.mvvm.core

import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedImageModel
import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedModel
import com.cazulabs.dogsapp.mvvm.data.breed.network.BreedResponse
import com.cazulabs.dogsapp.mvvm.data.dog.network.BreedImageResponse

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

    fun parseBreedImageResponseToBreedImageModel(
        breed: String,
        breedImageResponse: BreedImageResponse
    ): List<BreedImageModel> {
        val breeds = mutableListOf<BreedImageModel>()
        if (breedImageResponse.images.isNotEmpty()) {
            breedImageResponse.images.forEach { image ->
                breeds.add(BreedImageModel(breed, image))
            }
        }
        return breeds
    }

}