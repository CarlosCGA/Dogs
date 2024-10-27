package com.cazulabs.dogsapp.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cazulabs.dogsapp.mvvm.ui.viewmodel.BreedViewModel
import com.example.dogs.R

class BreedAdapterMVVM(
    private val breedViewModel: BreedViewModel
) : RecyclerView.Adapter<BreedViewHolderMVVM>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolderMVVM {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BreedViewHolderMVVM(layoutInflater.inflate(R.layout.item_breed, parent, false))
    }

    override fun onBindViewHolder(holder: BreedViewHolderMVVM, position: Int) {
        holder.bind(breedViewModel)
    }

    override fun getItemCount(): Int {
        return breedViewModel.breedModel.value?.size ?: 0
    }

}