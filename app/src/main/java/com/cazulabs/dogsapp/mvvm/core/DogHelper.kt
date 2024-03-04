package com.cazulabs.dogsapp.mvvm.core

import com.cazulabs.dogsapp.mvvm.data.dog.model.DogImageResponse
import com.cazulabs.dogsapp.mvvm.data.dog.model.DogModel

class DogHelper {

    fun parseDogImageResponseToDogModel(
        breed: String,
        subBreed: String,
        dogImageResponse: DogImageResponse
    ): List<DogModel> {
        val dogs = mutableListOf<DogModel>()
        if (dogImageResponse.images.isNotEmpty()) {
            dogImageResponse.images.forEach { image ->
                dogs.add(DogModel(breed, subBreed, image))
            }
        }
        return dogs
    }

}