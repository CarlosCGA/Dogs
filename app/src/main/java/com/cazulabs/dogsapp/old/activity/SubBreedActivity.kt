package com.cazulabs.dogsapp.old.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cazulabs.dogsapp.mvvm.core.ContextHelper
import com.cazulabs.dogsapp.mvvm.core.DogAPIConstants
import com.cazulabs.dogsapp.mvvm.core.RetrofitHelper
import com.cazulabs.dogsapp.old.APIService
import com.cazulabs.dogsapp.old.adapter.SubBreedAdapter
import com.example.dogs.databinding.ActivitySubBreedBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubBreedActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubBreedBinding
    private lateinit var adapter: SubBreedAdapter

    private var breed: String = ""
    private var subBreeds = mutableListOf<String>()
    private val dogImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBreedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ContextHelper.instance.setContext(context = this)

        getIntentExtraData()

        initRecyclerView()

        //getRandomImagesBySubBreed()
    }

    private fun getIntentExtraData() {
        breed = intent.getStringExtra(DogAPIConstants.BREED)!!
        subBreeds = intent.getStringArrayExtra(DogAPIConstants.SUB_BREED)!!.toMutableList()
    }

    private fun initRecyclerView() {
        adapter = SubBreedAdapter(breed, subBreeds, dogImages) //init recyclerView
        binding.rvSubBreeds.layoutManager = LinearLayoutManager(this)
        binding.rvSubBreeds.adapter = adapter
    }

    private fun getRandomImagesBySubBreed() {
        Log.d("CARLOS", "ANTES DE AÑADIR ADAPTER TIENE -> ${adapter.itemCount}")
        CoroutineScope(Dispatchers.IO).launch {
            var position = 0
            subBreeds.forEach { subBreed ->
                val imagesByBreedAndSubBreedCall = RetrofitHelper.instance.getRetrofit()
                    .create(APIService::class.java)
                    .getRandomDogImagesByBreedAndSubBreed(breed, subBreed, howMany = 1)

                if (imagesByBreedAndSubBreedCall.isSuccessful) {
                    runOnUiThread {
                        adapter.addImageToItem(
                            imagesByBreedAndSubBreedCall.body()!!.images[0],
                            position
                        )
                        position++
                    }
                }
            }
        }
    }
}