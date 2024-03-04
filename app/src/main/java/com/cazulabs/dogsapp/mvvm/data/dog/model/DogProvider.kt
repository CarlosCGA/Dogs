package com.cazulabs.dogsapp.mvvm.data.dog.model

import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedModel

class DogProvider {

    companion object {
        var dogs: List<DogModel> = emptyList()
    }
}