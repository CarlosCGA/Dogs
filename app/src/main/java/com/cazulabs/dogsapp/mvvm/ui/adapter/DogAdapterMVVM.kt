package com.cazulabs.dogsapp.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cazulabs.dogsapp.mvvm.ui.viewmodel.DogViewModel
import com.example.dogs.R

class DogAdapterMVVM(private val dogViewModel: DogViewModel): RecyclerView.Adapter<DogViewHolderMVVM>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolderMVVM {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolderMVVM(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }

    override fun onBindViewHolder(holder: DogViewHolderMVVM, position: Int) {
        val dog = dogViewModel.dogModel.value!![position]
        holder.bind(dog)
    }

    override fun getItemCount(): Int {
        return dogViewModel.dogModel.value?.size ?: 0
    }


}