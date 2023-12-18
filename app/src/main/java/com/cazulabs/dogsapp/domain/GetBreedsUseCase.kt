package com.cazulabs.dogsapp.domain

import com.cazulabs.dogsapp.data.BreedRepository
import com.cazulabs.dogsapp.data.model.BreedModel

class GetBreedsUseCase {

    private val repository = BreedRepository()

    suspend operator fun invoke(): List<BreedModel>? = repository.getAllBreeds()

}