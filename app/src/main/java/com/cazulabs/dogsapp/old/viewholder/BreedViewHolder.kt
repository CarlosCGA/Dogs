package com.cazulabs.dogsapp.old.viewholder

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cazulabs.dogsapp.mvvm.core.ContextHelper
import com.example.dogs.databinding.ItemBreedBinding
import com.cazulabs.dogsapp.mvvm.core.DogAPIConstants
import com.cazulabs.dogsapp.old.activity.DogActivity
import com.cazulabs.dogsapp.old.activity.SubBreedActivity
import com.squareup.picasso.Picasso

class BreedViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemBreedBinding.bind(view)

    fun bind(breed: String, image: String, subBreeds: List<String>) {
        if (image.isNotEmpty()) {
            Picasso.get().load(image).into(binding.ivDog)
            binding.viewLoading.animate().alpha(0F).withEndAction {
                binding.viewLoading.visibility = View.GONE
            }.start()
            binding.ivDog.animate().alpha(1F).start()
        }

        binding.tvBreed.text =
            breed.replaceFirst(breed.substring(0, 1), breed.substring(0, 1).uppercase())

        if (subBreeds.isEmpty()) {
            binding.btSubBreeds.visibility = View.GONE
            binding.btDogPictures.visibility = View.VISIBLE

            binding.btDogPictures.setOnClickListener {
                val intent =
                    Intent(ContextHelper.instance.getContext(), DogActivity::class.java)
                intent.putExtra(DogAPIConstants.BREED, breed)
                ContextHelper.instance.getContext()!!.startActivity(intent)
            }
        } else {
            binding.btSubBreeds.visibility = View.VISIBLE
            binding.btDogPictures.visibility = View.GONE

            binding.btSubBreeds.setOnClickListener {
                if (ContextHelper.instance.getContext() != null) {
                    val intent =
                        Intent(ContextHelper.instance.getContext(), SubBreedActivity::class.java)
                    intent.putExtra(DogAPIConstants.BREED, breed)
                    intent.putExtra(DogAPIConstants.SUB_BREED, subBreeds.toTypedArray())
                    ContextHelper.instance.getContext()!!.startActivity(intent)
                }
            }
        }
    }
}