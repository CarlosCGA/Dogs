package com.cazulabs.dogsapp.mvvm.domain

import com.cazulabs.dogsapp.mvvm.data.dog.DogRepository
import com.cazulabs.dogsapp.mvvm.data.dog.model.DogModel

class GetAllDogsByBreedAndSubBreedUseCase {

    private val repository = DogRepository()

    suspend operator fun invoke(breed: String, subBreed: String, howMany: Int): List<DogModel> =
        repository.getAllDogsByBreedAndSubBreed(breed, subBreed, howMany)

}