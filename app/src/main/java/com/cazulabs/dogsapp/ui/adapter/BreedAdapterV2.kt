package com.cazulabs.dogsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cazulabs.dogsapp.data.model.BreedModel
import com.example.dogs.R

class BreedAdapterV2(
    private val breeds: List<BreedModel>
    ) : RecyclerView.Adapter<BreedViewHolderV2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolderV2 {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BreedViewHolderV2(layoutInflater.inflate(R.layout.item_breed, parent, false))
    }

    override fun onBindViewHolder(holder: BreedViewHolderV2, position: Int) {
        holder.bind(this, breeds[position])
    }

    override fun getItemCount(): Int {
        return breeds.size
    }

    fun setImage(image: String, position: Int) {
        if(position < itemCount)
            this.breeds[position].image = image
    }

}