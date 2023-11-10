package com.example.dogs

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dogs.databinding.ItemBreedBinding
import com.example.dogs.utils.DogConstants
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

        binding.cvParent.setOnClickListener {
            if (subBreeds.isNotEmpty()) {
                if (ContextInstance.instance.getContext() != null) {
                    val intent =
                        Intent(ContextInstance.instance.getContext(), SubBreedActivity::class.java)
                    intent.putExtra(DogConstants.BREED, breed)
                    intent.putExtra(DogConstants.SUB_BREEDS, subBreeds.toTypedArray())
                    ContextInstance.instance.getContext()!!.startActivity(intent)
                }
            }
        }
    }
}