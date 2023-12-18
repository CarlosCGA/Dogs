package com.cazulabs.dogsapp.data.network

import com.cazulabs.dogsapp.core.BreedHelper
import com.cazulabs.dogsapp.core.RetrofitHelper
import com.cazulabs.dogsapp.data.model.BreedModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BreedService {

    private val retrofit = RetrofitHelper.instance.getRetrofit()

    suspend fun getBreeds(): List<BreedModel> {
         return withContext(Dispatchers.IO) {
             val response = retrofit.create(BreedApiClient::class.java).getAllBreeds()
             val breeds = response.body()?.let { BreedHelper().parseBreedResponseToBreedModel(it) }
             breeds ?: emptyList()
         }
    }

}