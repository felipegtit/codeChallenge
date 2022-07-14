package com.felipegsolutions.codechallenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import com.felipegsolutions.codechallenge.databinding.ActivityDetailsBinding
import com.felipegsolutions.codechallenge.model.Pics
import com.felipegsolutions.codechallenge.viewmodel.DetailsActivityViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    val viewModel: DetailsActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val position = intent.getIntExtra("position", -1)
        if (position > -1) {
            val hit = viewModel.getSelectedHit(position)
            binding.apply {
                Picasso.get().load(hit.largeImageURL).into(imageViewDetails)
                hitDetail = hit
            }
        }
    }
}