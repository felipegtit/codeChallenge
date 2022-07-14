package com.felipegsolutions.codechallenge.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.felipegsolutions.codechallenge.model.Hit
import com.felipegsolutions.codechallenge.model.Pics

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsActivityViewModel @Inject constructor(private val liveDataPics: MutableLiveData<Pics>): ViewModel() {

    fun getLiveDataObserver(): MutableLiveData<Pics> {
        return liveDataPics
    }

    fun getSelectedHit(position: Int): Hit {
        return liveDataPics.value!!.hits[position]
    }
}