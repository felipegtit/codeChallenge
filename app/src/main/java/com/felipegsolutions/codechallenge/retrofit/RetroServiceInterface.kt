package com.felipegsolutions.codechallenge.retrofit

import com.felipegsolutions.codechallenge.model.Pics
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {

    @GET("api/?key=28614023-97bf3e8f3ffa8e57ea87f2b0c&image_type=photo")
    fun getImages(@Query("q") tags: String): Call<Pics>
}