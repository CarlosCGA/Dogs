package com.cazulabs.dogsapp.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cazulabs.dogsapp.mvvm.ui.viewmodel.BreedViewModel
import com.example.dogs.R

class BreedAdapterV4(
    private val breedViewModel: BreedViewModel
) : RecyclerView.Adapter<BreedViewHolderV4>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolderV4 {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BreedViewHolderV4(layoutInflater.inflate(R.layout.item_breed, parent, false))
    }

    override fun onBindViewHolder(holder: BreedViewHolderV4, position: Int) {
        holder.bind(breedViewModel)
    }

    override fun getItemCount(): Int {
        return breedViewModel.breedModel.value?.size ?: 0
    }

}