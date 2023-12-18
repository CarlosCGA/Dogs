package com.cazulabs.dogsapp.mvvm.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedModel
import com.cazulabs.dogsapp.mvvm.domain.GetBreedsUseCase
import kotlinx.coroutines.launch

class BreedViewModel : ViewModel() {

    val breedModel = MutableLiveData<List<BreedModel>>()

    var getBreedsUseCase = GetBreedsUseCase()

    @SuppressLint("NullSafeMutableLiveData")
    fun onCreate() {
        viewModelScope.launch {
            val result = getBreedsUseCase()

            if (!result.isNullOrEmpty()) {
                breedModel.postValue(result)
            }
        }
    }

}