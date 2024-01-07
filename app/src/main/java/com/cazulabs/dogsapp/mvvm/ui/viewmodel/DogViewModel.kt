package com.cazulabs.dogsapp.mvvm.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cazulabs.dogsapp.mvvm.data.dog.model.DogModel
import com.cazulabs.dogsapp.mvvm.domain.GetAllDogsByBreedUseCase
import kotlinx.coroutines.launch

class DogViewModel : ViewModel() {

    val dogModel = MutableLiveData<List<DogModel>>()

    //use cases
    val getAllDogsByBreedUseCase = GetAllDogsByBreedUseCase

    @SuppressLint("NullSafeMutableLiveData")
    fun onCreate(breed: String, subBreed: String) {
        viewModelScope.launch {
            val result = getAllDogsByBreedUseCase()

            if (!result.isNullOrEmpty()) {
                breedModel.postValue(result)
            }
        }
    }

    fun getDogs() {
        //val dogs = APIService.getDogsByBreed("") //ESPERAR A LA INTRODUCCION DE RETROFIT EN EL CAPITULO 2
        //dogModel.postValue(dogs)
    }

}