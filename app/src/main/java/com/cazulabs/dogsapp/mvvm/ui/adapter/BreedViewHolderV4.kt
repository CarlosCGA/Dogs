package com.cazulabs.dogsapp.mvvm.ui.adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cazulabs.dogsapp.mvvm.core.ContextHelper
import com.cazulabs.dogsapp.mvvm.core.DogAPIConstants
import com.cazulabs.dogsapp.mvvm.ui.viewmodel.BreedViewModel
import com.cazulabs.dogsapp.old.activity.DogActivity
import com.cazulabs.dogsapp.old.activity.SubBreedActivity
import com.example.dogs.databinding.ItemBreedBinding
import com.squareup.picasso.Picasso

class BreedViewHolderV4(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemBreedBinding.bind(view)

    fun bind(breedViewModel: BreedViewModel) {
        val breedModel = breedViewModel.breedModel.value!![adapterPosition]

        if (breedModel.image.isEmpty())
            breedViewModel.setBreedImage(this, breedModel.breed, adapterPosition)
        else
            loadImage(breedModel.image)

        binding.tvBreed.text =
            breedModel.breed.replaceFirst(
                breedModel.breed.substring(0, 1),
                breedModel.breed.substring(0, 1).uppercase()
            )

        if (breedModel.subBreeds.isEmpty()) {
            binding.btSubBreeds.visibility = View.GONE
            binding.btDogPictures.visibility = View.VISIBLE

            binding.btDogPictures.setOnClickListener {
                val intent =
                    Intent(ContextHelper.instance.getContext(), DogActivity::class.java)
                intent.putExtra(DogAPIConstants.BREED, breedModel.breed)
                ContextHelper.instance.getContext()!!.startActivity(intent)
            }
        } else {
            binding.btSubBreeds.visibility = View.VISIBLE
            binding.btDogPictures.visibility = View.GONE

            binding.btSubBreeds.setOnClickListener {
                if (ContextHelper.instance.getContext() != null) {
                    val intent =
                        Intent(ContextHelper.instance.getContext(), SubBreedActivity::class.java)
                    intent.putExtra(DogAPIConstants.BREED, breedModel.breed)
                    intent.putExtra(DogAPIConstants.SUB_BREED, breedModel.subBreeds.toTypedArray())
                    ContextHelper.instance.getContext()!!.startActivity(intent)
                }
            }
        }
    }

    fun loadImage(image: String) {
        Picasso.get().load(image).into(binding.ivDog)
        binding.viewLoading.animate().alpha(0F).withEndAction {
            binding.viewLoading.visibility = View.GONE
        }.start()
        binding.ivDog.animate().alpha(1F).start()
    }
}