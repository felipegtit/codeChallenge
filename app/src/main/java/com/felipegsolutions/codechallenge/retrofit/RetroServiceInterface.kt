package com.felipegsolutions.codechallenge.retrofit

import com.felipegsolutions.codechallenge.model.Pics
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("api/?key=28614023-97bf3e8f3ffa8e57ea87f2b0c&q=yellow+flowers&image_type=photo")
    fun getImages(): Call<Pics>
}