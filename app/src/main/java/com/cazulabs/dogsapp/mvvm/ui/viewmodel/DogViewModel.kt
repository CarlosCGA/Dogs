package com.cazulabs.dogsapp.mvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cazulabs.dogsapp.mvvm.data.dog.model.DogModel

class DogViewModel : ViewModel() {

    val dogModel = MutableLiveData<DogModel>()

    fun getDogs() {
        //val dogs = APIService.getDogsByBreed("") //ESPERAR A LA INTRODUCCION DE RETROFIT EN EL CAPITULO 2
        //dogModel.postValue(dogs)
    }

}