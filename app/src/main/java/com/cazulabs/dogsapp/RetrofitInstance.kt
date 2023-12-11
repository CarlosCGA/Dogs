package com.cazulabs.dogsapp

import com.cazulabs.dogsapp.utils.DogAPIEndpoints
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance private constructor(){
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(DogAPIEndpoints.BASE_URL) //Base URL of API
        .addConverterFactory(GsonConverterFactory.create()) //Use GsonConverter library
        .build()

    companion object {
        val instance: RetrofitInstance by lazy {
            RetrofitInstance()
        }
    }

    fun getRetrofit(): Retrofit {
        return retrofit
    }
}