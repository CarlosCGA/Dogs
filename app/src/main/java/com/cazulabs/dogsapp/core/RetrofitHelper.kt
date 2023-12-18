package com.cazulabs.dogsapp.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper private constructor(){
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(DogAPIEndpoints.BASE_URL) //Base URL of API
        .addConverterFactory(GsonConverterFactory.create()) //Use GsonConverter library
        .build()

    companion object {
        val instance: RetrofitHelper by lazy {
            RetrofitHelper()
        }
    }

    fun getRetrofit(): Retrofit {
        return retrofit
    }
}