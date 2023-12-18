package com.cazulabs.dogsapp.ui.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cazulabs.dogsapp.data.model.old.APIService
import com.cazulabs.dogsapp.ui.adapter.BreedAdapter
import com.cazulabs.dogsapp.core.ContextHelper
import com.cazulabs.dogsapp.core.RetrofitHelper
import com.cazulabs.dogsapp.data.model.BreedModel
import com.cazulabs.dogsapp.ui.adapter.BreedAdapterV2
import com.cazulabs.dogsapp.ui.adapter.BreedAdapterV3
import com.cazulabs.dogsapp.ui.viewmodel.BreedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.dogs.databinding.ActivityMainBinding

class MainActivityMVVM : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BreedAdapterV3

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
        adapter = BreedAdapterV3(breedViewModel.breedModel) //init recyclerView empty
        binding.rvBreeds.layoutManager = LinearLayoutManager(this)
        binding.rvBreeds.adapter = adapter
    }

}