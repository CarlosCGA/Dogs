package com.cazulabs.dogsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.cazulabs.dogsapp.data.model.BreedModel
import com.example.dogs.R

class BreedAdapterV3(
    private val breeds: MutableLiveData<List<BreedModel>>
) : RecyclerView.Adapter<BreedViewHolderV3>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolderV3 {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BreedViewHolderV3(layoutInflater.inflate(R.layout.item_breed, parent, false))
    }

    override fun onBindViewHolder(holder: BreedViewHolderV3, position: Int) {
        holder.bind(this, (breeds.value?.get(position) ?: BreedModel("", "", emptyList())))
    }

    override fun getItemCount(): Int {
        return breeds.value?.size ?: 0
    }

    fun setImage(image: String, position: Int) {
        if (position < itemCount)
            this.breeds.value!![position].image = image
    }

}