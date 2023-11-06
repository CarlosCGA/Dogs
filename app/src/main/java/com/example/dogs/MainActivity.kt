package com.example.dogs

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogs.utils.DogAPIEndpoints
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.dogs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()/*, SearchView.OnQueryTextListener*/ {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BreedAdapter
    private val dogImages = mutableListOf<String>()
    private var breedsMapdog = mutableListOf<Pair<String, List<String>>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        breedsMapdog.toList()
        initRecyclerView()
        getAllBreeds()

        //binding.svDogs.setOnQueryTextListener(this)
    }


    private fun initRecyclerView() {
        adapter = BreedAdapter(breedsMapdog, dogImages) //init recyclerView empty
        binding.rvBreeds.layoutManager = LinearLayoutManager(this)
        binding.rvBreeds.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getAllBreeds() {
        binding.rvBreeds.animate().alpha(0F).start()
        binding.viewLoading.animate().alpha(1F).start()

        CoroutineScope(Dispatchers.IO).launch {
            val allBreedsCall =
                RetrofitInstance.instance.getRetrofit().create(APIService::class.java)
                    .getAllBreeds()
            val breeds = allBreedsCall.body()!!.breeds
            if (allBreedsCall.isSuccessful) {
                val images = mutableListOf<String>()
                breeds.keys.forEach { breed ->
                    val imagesByBreedCall = RetrofitInstance.instance.getRetrofit()
                        .create(APIService::class.java)
                        .getRandomDogImagesByBreed(breed, count = 1)
                    images.add(imagesByBreedCall.body()!!.images[0])
                }

                runOnUiThread {
                    if (breeds.isNotEmpty()) {
                        breedsMapdog.addAll(breeds.toList().toMutableList())
                        dogImages.addAll(images)
                        adapter.notifyDataSetChanged()

                        binding.viewLoading.animate().alpha(0F).start()
                        binding.rvBreeds.animate().alpha(1F).start()
                    }
                }

            }
        }
    }

    //Hide keyboard
    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    //Show info about query error
    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

/*
    //Execute query to get list of url images of dogs by breed
    @SuppressLint("NotifyDataSetChanged")
    private fun searchByBreed(query: String) {
        binding.rvDogs.animate().alpha(0F).start()
        binding.viewLoading.animate().alpha(1F).start()

        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getDogsByBreed(query)
            val dogs = call.body()
            runOnUiThread { //Runs in main thread
                binding.viewLoading.animate().alpha(0F).start()

                if (call.isSuccessful) {
                    val images = dogs?.images ?: emptyList()
                    dogImages.clear()
                    dogImages.addAll(images)
                    adapter.notifyDataSetChanged()
                    binding.rvDogs.animate().alpha(1F).start()
                    hideKeyboard()
                } else
                    showError()
            }
        }
    }
    */

    /*
    //Executed when SearchView submitted
    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty())
            searchByBreed(query.lowercase(Locale.ROOT))

        return true
    }

    //Executed when text in SearchView change
    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
    */

}