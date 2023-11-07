package com.example.dogs

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BreedAdapter(
    private val breedsWithSubBreeds: MutableList<Pair<String, List<String>>>,
    private val images: MutableList<String>
) : RecyclerView.Adapter<BreedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BreedViewHolder(layoutInflater.inflate(R.layout.item_breed, parent, false))
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bind(breedsWithSubBreeds[position].first, images[position])
    }

    override fun getItemCount(): Int {
        return breedsWithSubBreeds.size
    }

    fun addItem(breedWithSubBreeds: Pair<String, List<String>>, image: String, position: Int) {
        this.breedsWithSubBreeds.add(breedWithSubBreeds)
        images.add(image)
        notifyItemInserted(position)
    }

}