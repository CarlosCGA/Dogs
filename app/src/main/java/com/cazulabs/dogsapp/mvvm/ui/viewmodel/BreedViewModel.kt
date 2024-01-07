package com.cazulabs.dogsapp.mvvm.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedModel
import com.cazulabs.dogsapp.mvvm.domain.GetBreedImageUseCase
import com.cazulabs.dogsapp.mvvm.domain.GetBreedsUseCase
import com.cazulabs.dogsapp.mvvm.ui.adapter.BreedViewHolderV4
import kotlinx.coroutines.launch

class BreedViewModel : ViewModel() {

    val breedModel = MutableLiveData<List<BreedModel>>()

    var getBreedsUseCase = GetBreedsUseCase()
    var getBreedImageUseCase = GetBreedImageUseCase()

    @SuppressLint("NullSafeMutableLiveData")
    fun onCreate() {
        viewModelScope.launch {
            val result = getBreedsUseCase()

            if (!result.isNullOrEmpty()) {
                breedModel.postValue(result)
            }
        }
    }

    fun setBreedImage(viewHolder: BreedViewHolderV4, breed: String, position: Int) {
        viewModelScope.launch {
            val result = getBreedImageUseCase(breed, howMany = 1)

            if (result.isNotEmpty()) {
                breedModel.value!![position].image = result[0].image
                viewHolder.loadImage(result[0].image)
            }
        }
    }

}