package com.example.dogs.utils

object DogAPIEndpoints {
    const val BASE_URL= "https://dog.ceo/api/"
    const val LIST_ALL_BREEDS= "breeds/list/all"
    const val GET_ALL_IMAGES_OF_BREED= "breed/{breed}/images"
    const val GET_RANDOM_DOG_IMAGES_BY_BREED = "breed/{breed}/images/random/{count}"
}