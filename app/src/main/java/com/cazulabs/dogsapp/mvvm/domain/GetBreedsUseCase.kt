package com.cazulabs.dogsapp.mvvm.domain

import com.cazulabs.dogsapp.mvvm.data.breed.BreedRepository
import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedModel

class GetBreedsUseCase {

    private val repository = BreedRepository()

    suspend operator fun invoke(): List<BreedModel>? = repository.getAllBreeds()

}