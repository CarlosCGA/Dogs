package com.cazulabs.dogsapp.old.viewholder

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cazulabs.dogsapp.mvvm.core.ContextHelper
import com.cazulabs.dogsapp.mvvm.core.DogAPIConstants
import com.cazulabs.dogsapp.mvvm.core.RetrofitHelper
import com.cazulabs.dogsapp.mvvm.ui.view.DogActivityMVVM
import com.cazulabs.dogsapp.old.APIService
import com.cazulabs.dogsapp.old.activity.SubBreedActivity
import com.cazulabs.dogsapp.old.adapter.SubBreedAdapter
import com.example.dogs.R
import com.example.dogs.databinding.ItemSubBreedBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubBreedViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSubBreedBinding.bind(view)

    fun bind(adapter: SubBreedAdapter, breed: String, subBreed: String, image: String) {
        if (image.isNotEmpty()) {
            setDogImage(image)
        } else
            getRandomImageBySubBreed(adapter, breed, subBreed)

        binding.tvBreed.text = ContextHelper.instance.getContext()!!.getString(
            R.string.breed_subBreed_input,
            breed.replaceFirst(breed.substring(0, 1), breed.substring(0, 1).uppercase()),
            subBreed.replaceFirst(subBreed.substring(0, 1), subBreed.substring(0, 1).uppercase())
        )

        binding.cvBreed.setOnClickListener {
            if (ContextHelper.instance.getContext() != null) {
                val intent =
                    Intent(ContextHelper.instance.getContext(), DogActivityMVVM::class.java)
                intent.putExtra(DogAPIConstants.BREED, breed)
                intent.putExtra(DogAPIConstants.SUB_BREED, subBreed)
                ContextHelper.instance.getContext()!!.startActivity(intent)
            }
        }
    }

    private fun getRandomImageBySubBreed(
        adapter: SubBreedAdapter,
        breed: String,
        subBreed: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val imagesByBreedAndSubBreedCall = RetrofitHelper.instance.getRetrofit()
                .create(APIService::class.java)
                .getRandomDogImagesByBreedAndSubBreed(breed, subBreed, howMany = 1)

            if (imagesByBreedAndSubBreedCall.isSuccessful) {
                val image = imagesByBreedAndSubBreedCall.body()!!.images[0]
                setDogImage(image)
                adapter.addImageToItem(image, adapterPosition)
            }
        }
    }

    private fun setDogImage(image: String) {
        val context = (ContextHelper.instance.getContext()) as SubBreedActivity
        context.runOnUiThread {
            Picasso.get().load(image).into(binding.ivDog)
            binding.viewLoading.animate().alpha(0F)
            binding.ivDog.animate().alpha(1F)
        }
    }
}