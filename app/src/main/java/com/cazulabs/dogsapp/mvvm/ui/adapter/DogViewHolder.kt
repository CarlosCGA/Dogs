package com.cazulabs.dogsapp.mvvm.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cazulabs.dogsapp.mvvm.data.dog.model.DogModel
import com.example.dogs.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemDogBinding.bind(view)

    fun bind(dog: DogModel) {
        val image = dog.image
        Picasso.get().load(image).into(binding.ivDog)
    }

}