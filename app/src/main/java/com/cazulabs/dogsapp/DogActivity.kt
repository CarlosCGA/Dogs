package com.cazulabs.dogsapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogs.databinding.ActivityDogImagelistBinding
import com.cazulabs.dogsapp.utils.DogConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDogImagelistBinding
    private lateinit var breed: String

    private val dogImages = mutableListOf<String>()
    private lateinit var adapter: DogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogImagelistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ContextInstance.instance.setContext(context = this)

        breed = this.intent?.getStringExtra(DogConstants.BREED) ?: ""

        if (breed.isEmpty())
            showError()
        else
            initRecyclerView()

    }

    private fun initRecyclerView() {
        adapter = DogAdapter(dogImages)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter
        getDogs()
    }

    private fun getDogs() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitInstance.instance.getRetrofit().create(APIService::class.java)
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