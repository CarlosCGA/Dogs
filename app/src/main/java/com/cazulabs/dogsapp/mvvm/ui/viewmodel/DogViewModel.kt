package com.cazulabs.dogsapp.mvvm.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cazulabs.dogsapp.mvvm.data.dog.model.DogModel
import com.cazulabs.dogsapp.mvvm.domain.GetAllDogsByBreedAndSubBreedUseCase
import kotlinx.coroutines.launch

class DogViewModel : ViewModel() {

    val dogModel = MutableLiveData<List<DogModel>>()

    //use cases
    val getAllDogsByBreedAndSubBreedUseCase = GetAllDogsByBreedAndSubBreedUseCase()

    @SuppressLint("NullSafeMutableLiveData")
    fun onCreate(breed: String, subBreed: String) {
        viewModelScope.launch {
            val result = getAllDogsByBreedAndSubBreedUseCase(breed, subBreed, howMany = 50)

            if (result.isNotEmpty()) {
                dogModel.postValue(result)
            }
        }
    }

    fun getDogs() {
        //val dogs = APIService.getDogsByBreed("") //ESPERAR A LA INTRODUCCION DE RETROFIT EN EL CAPITULO 2
        //dogModel.postValue(dogs)
    }

}