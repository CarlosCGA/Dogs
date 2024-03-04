package com.cazulabs.dogsapp.mvvm.data.dog.network

import com.cazulabs.dogsapp.mvvm.core.DogHelper
import com.cazulabs.dogsapp.mvvm.core.RetrofitHelper
import com.cazulabs.dogsapp.mvvm.data.dog.model.DogModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogService {

    private val retrofit = RetrofitHelper.instance.getRetrofit()

    suspend fun getAllDogsByBreedAndSubBreed(
        breed: String,
        subBreed: String,
        howMany: Int
    ): List<DogModel> {
        return withContext(Dispatchers.IO) {
            val response =
                retrofit.create(DogApiClient::class.java)
                    .getAllDogsByBreedAndSubBreed(breed, subBreed, howMany)

            val dogs = response.body()?.let {
                DogHelper().parseDogImageResponseToDogModel(breed, subBreed, it)
            }
            dogs ?: emptyList()
        }
    }

}