package com.cazulabs.dogsapp.mvvm.domain

import com.cazulabs.dogsapp.mvvm.data.breed.BreedRepository
import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedImageModel

class GetBreedImageUseCase {

    private val repository = BreedRepository()

    suspend operator fun invoke(breed: String, howMany: Int): List<BreedImageModel> =
        repository.getBreedImage(breed, howMany)

}