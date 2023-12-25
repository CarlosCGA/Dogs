package com.cazulabs.dogsapp.mvvm.data.breed.network

import com.cazulabs.dogsapp.mvvm.core.BreedHelper
import com.cazulabs.dogsapp.mvvm.core.RetrofitHelper
import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedImageModel
import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BreedService {

    private val retrofit = RetrofitHelper.instance.getRetrofit()

    suspend fun getAllBreeds(): List<BreedModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(BreedApiClient::class.java).getAllBreeds()
            val breeds = response.body()?.let { BreedHelper().parseBreedResponseToBreedModel(it) }
            breeds ?: emptyList()
        }
    }

    suspend fun getRandomBreedImages(breed: String, howMany: Int): List<BreedImageModel> {
        return withContext(Dispatchers.IO) {
            val response =
                retrofit.create(BreedApiClient::class.java).getRandomBreedImages(breed, howMany)
            val images = response.body()?.let {
                BreedHelper().parseBreedImageResponseToBreedImageModel(breed, it)
            }
            images ?: emptyList()
        }
    }

}