package com.felipegsolutions.codechallenge.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object {
        val BASE_URL = "https://pixabay.com"///api/?key=28614023-97bf3e8f3ffa8e57ea87f2b0c&q=yellow+flowers&image_type=photo"

        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}