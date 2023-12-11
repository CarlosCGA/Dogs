package com.cazulabs.dogsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogs.databinding.ActivitySubBreedBinding
import com.cazulabs.dogsapp.utils.DogConstants
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
        ContextInstance.instance.setContext(context = this)

        getIntentExtraData()

        initRecyclerView()

        //getRandomImagesBySubBreed()
    }

    private fun getIntentExtraData() {
        breed = intent.getStringExtra(DogConstants.BREED)!!
        subBreeds = intent.getStringArrayExtra(DogConstants.SUB_BREEDS)!!.toMutableList()
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
                val imagesByBreedAndSubBreedCall = RetrofitInstance.instance.getRetrofit()
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

/*
package com.example.dogs

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogs.databinding.ActivitySubBreedBinding
import com.example.dogs.utils.DogConstants
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
        ContextInstance.instance.setContext(context = this)

        initRecyclerView()

        getRandomImagesBySubBreed()
    }

    private fun initRecyclerView() {
        adapter = SubBreedAdapter(breed, subBreeds, dogImages) //init recyclerView
        binding.rvSubBreeds.layoutManager = LinearLayoutManager(this)
        binding.rvSubBreeds.adapter = adapter
    }

    private fun getRandomImagesBySubBreed() {
        val intentBreed = intent.getStringExtra(DogConstants.BREED)!!
        val intentSubBreeds = intent.getStringArrayExtra(DogConstants.SUB_BREEDS)!!.toMutableList()

        Log.d("CARLOS", "ANTES DE AÑADIR ADAPTER TIENE -> ${adapter.itemCount}")
        CoroutineScope(Dispatchers.IO).launch {
            var position = 0
            intentSubBreeds.forEach { subBreed ->
                val imagesByBreedAndSubBreedCall = RetrofitInstance.instance.getRetrofit()
                    .create(APIService::class.java)
                    .getRandomDogImagesByBreedAndSubBreed(intentBreed, subBreed, howMany = 1)

                if (imagesByBreedAndSubBreedCall.isSuccessful) {
                    runOnUiThread {
                        adapter.addItem(
                            intentBreed,
                            subBreed,
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
 */