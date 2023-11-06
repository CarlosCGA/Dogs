package com.example.dogs

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dogs.databinding.ItemBreedBinding
import com.squareup.picasso.Picasso

class BreedViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemBreedBinding.bind(view)

    fun bind(breed: String, image: String) {
        Picasso.get().load(image).into(binding.ivDog)
        binding.tvBreed.text =
            breed.replaceFirst(breed.substring(0, 1), breed.substring(0, 1).uppercase())
    }
}