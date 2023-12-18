package com.cazulabs.dogsapp.old.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cazulabs.dogsapp.old.viewholder.SubBreedViewHolder
import com.example.dogs.R

class SubBreedAdapter(
    private var breed: String,
    private val subBreeds: MutableList<String>,
    private val images: MutableList<String>
) : RecyclerView.Adapter<SubBreedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubBreedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SubBreedViewHolder(layoutInflater.inflate(R.layout.item_sub_breed, parent, false))
    }

    override fun onBindViewHolder(holder: SubBreedViewHolder, position: Int) {
        holder.bind(this, breed, subBreeds[position], if(position < images.size) images[position] else "")
    }

    override fun getItemCount(): Int {
        return subBreeds.size
    }

    fun addItem(breed: String, subBreed: String, image: String, position: Int) {
        this.breed = breed
        subBreeds.add(subBreed)
        images.add(image)
        notifyItemInserted(position)
    }

    fun addImageToItem(image: String, position: Int) {
        if(position < itemCount) {
            if(position < images.size)
                this.images[position] = image
            else
                this.images.add(image)
        }
    }

}