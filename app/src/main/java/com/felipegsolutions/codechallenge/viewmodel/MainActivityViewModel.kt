package com.felipegsolutions.codechallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.felipegsolutions.codechallenge.model.Pics
import com.felipegsolutions.codechallenge.retrofit.RetroInstance
import com.felipegsolutions.codechallenge.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    private var liveDataPics: MutableLiveData<Pics> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<Pics> {
        return liveDataPics
    }

    fun makeApiCAll(tags: String) {
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)

        val call = retroService.getImages(tags)

        call.enqueue(object : Callback<Pics> {

            override fun onResponse(call: Call<Pics>, response: Response<Pics>) {
                liveDataPics.postValue(response.body())
            }

            override fun onFailure(call: Call<Pics>, t: Throwable) {
                liveDataPics.postValue(null)
            }
        })
    }
}