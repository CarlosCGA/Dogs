package com.cazulabs.dogsapp.mvvm.ui.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cazulabs.dogsapp.mvvm.core.ContextHelper
import com.cazulabs.dogsapp.mvvm.ui.adapter.BreedAdapterMVVM
import com.cazulabs.dogsapp.mvvm.ui.viewmodel.BreedViewModel
import com.example.dogs.databinding.ActivityMainBinding

class MainActivityMVVM : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BreedAdapterMVVM

    private val breedViewModel : BreedViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ContextHelper.instance.setContext(context = this)

        initRecyclerView()

        breedViewModel.onCreate()
        breedViewModel.breedModel.observe(this) {
            adapter.notifyDataSetChanged()
        }

    }

    private fun initRecyclerView() {
        adapter = BreedAdapterMVVM(breedViewModel) //init recyclerView empty
        binding.rvBreeds.layoutManager = LinearLayoutManager(this)
        binding.rvBreeds.adapter = adapter
    }

}