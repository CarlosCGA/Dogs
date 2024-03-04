package com.cazulabs.dogsapp.mvvm.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cazulabs.dogsapp.mvvm.core.ContextHelper
import com.cazulabs.dogsapp.mvvm.core.DogAPIConstants
import com.cazulabs.dogsapp.mvvm.core.RetrofitHelper
import com.cazulabs.dogsapp.mvvm.ui.viewmodel.DogViewModel
import com.cazulabs.dogsapp.old.APIService
import com.cazulabs.dogsapp.old.adapter.DogAdapter
import com.example.dogs.databinding.ActivityDogImagelistBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DogActivityMVVM : AppCompatActivity() {

    private lateinit var binding: ActivityDogImagelistBinding
    private lateinit var breed: String
    private lateinit var subBreed: String

    private val dogImages = mutableListOf<String>()
    private lateinit var adapter: DogAdapter

    private val dogViewModel: DogViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogImagelistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ContextHelper.instance.setContext(context = this)

        getIntentExtraData()

        dogViewModel.onCreate(breed, subBreed)
        dogViewModel.dogModel.observe(this) {
            adapter.notifyDataSetChanged()
        }

        if (breed.isEmpty())
            showError()
        else {
            if (subBreed.isNotEmpty())
                initRecyclerView()
            else {
                //TODO GET DOG IMAGES BY BREED WITHOUT SUB_BREED USE CASE
            }
        }
    }

    private fun getIntentExtraData() {
        breed = intent.getStringExtra(DogAPIConstants.BREED)!!
        subBreed = intent.getStringExtra(DogAPIConstants.SUB_BREED)!!
    }

    private fun initRecyclerView() {
        adapter = DogAdapter(dogImages) //TODO UPDATE ADAPTER
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter
        getDogs()
    }

    private fun getDogs() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitHelper.instance.getRetrofit().create(APIService::class.java)
                .getDogsByBreed(breed)
            val dogs = call.body()
            runOnUiThread {

                if (call.isSuccessful) {
                    val images = dogs?.images ?: emptyList()
                    dogImages.clear()
                    dogImages.addAll(images)
                    adapter.notifyDataSetChanged()
                    binding.rvDogs.animate().alpha(1F).start()
                } else
                    showError()
            }
        }
    }

    //Show info about query error
    private fun showError() {
        //TODO UPGRADE ISSUES
        Toast.makeText(this, "An error has ocurred", Toast.LENGTH_SHORT).show()
    }
}