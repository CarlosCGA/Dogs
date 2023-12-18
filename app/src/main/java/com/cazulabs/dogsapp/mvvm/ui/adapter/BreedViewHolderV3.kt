package com.cazulabs.dogsapp.mvvm.ui.adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cazulabs.dogsapp.mvvm.core.ContextHelper
import com.example.dogs.databinding.ItemBreedBinding
import com.cazulabs.dogsapp.mvvm.core.DogConstants
import com.cazulabs.dogsapp.mvvm.core.RetrofitHelper
import com.cazulabs.dogsapp.mvvm.data.breed.model.BreedModel
import com.cazulabs.dogsapp.old.APIService
import com.cazulabs.dogsapp.old.activity.DogActivity
import com.cazulabs.dogsapp.mvvm.ui.view.MainActivityMVVM
import com.cazulabs.dogsapp.old.activity.SubBreedActivity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BreedViewHolderV3(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemBreedBinding.bind(view)

    fun bind(adapter: BreedAdapterV3, breedModel: BreedModel) {
        if (breedModel.image.isEmpty()) {
            getImage(adapter, breedModel.breed)
        }
        else
            loadImage(breedModel.image)

        binding.tvBreed.text =
            breedModel.breed.replaceFirst(breedModel.breed.substring(0, 1), breedModel.breed.substring(0, 1).uppercase())

        if (breedModel.subBreeds.isEmpty()) {
            binding.btSubBreeds.visibility = View.GONE
            binding.btDogPictures.visibility = View.VISIBLE

            binding.btDogPictures.setOnClickListener {
                val intent =
                    Intent(ContextHelper.instance.getContext(), DogActivity::class.java)
                intent.putExtra(DogConstants.BREED, breedModel.breed)
                ContextHelper.instance.getContext()!!.startActivity(intent)
            }
        } else {
            binding.btSubBreeds.visibility = View.VISIBLE
            binding.btDogPictures.visibility = View.GONE

            binding.btSubBreeds.setOnClickListener {
                if (ContextHelper.instance.getContext() != null) {
                    val intent =
                        Intent(ContextHelper.instance.getContext(), SubBreedActivity::class.java)
                    intent.putExtra(DogConstants.BREED, breedModel.breed)
                    intent.putExtra(DogConstants.SUB_BREEDS, breedModel.subBreeds.toTypedArray())
                    ContextHelper.instance.getContext()!!.startActivity(intent)
                }
            }
        }
    }

    private fun getImage(
        adapter: BreedAdapterV3,
        breed: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val imageCall = RetrofitHelper.instance.getRetrofit()
                .create(APIService::class.java)
                .getRandomDogImagesByBreed(breed, howMany = 1)

            if (imageCall.isSuccessful) {
                val image = imageCall.body()!!.images[0]
                val context = ContextHelper.instance.getContext() as MainActivityMVVM
                context.runOnUiThread {
                    loadImage(image)
                }
                adapter.setImage(image, adapterPosition)
            }
        }
    }

    private fun loadImage(image: String) {
        Picasso.get().load(image).into(binding.ivDog)
        binding.viewLoading.animate().alpha(0F).withEndAction {
            binding.viewLoading.visibility = View.GONE
        }.start()
        binding.ivDog.animate().alpha(1F).start()
    }
}