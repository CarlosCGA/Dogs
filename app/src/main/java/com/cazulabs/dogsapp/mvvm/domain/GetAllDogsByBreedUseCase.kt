package com.cazulabs.dogsapp.mvvm.domain

import com.cazulabs.dogsapp.mvvm.data.dog.model.DogModel

class GetAllDogsByBreedUseCase {

    private val repository = DogRepository()

    suspend operator fun invoke(breed: String, subBreed: String): List<DogModel> =

}