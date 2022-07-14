package com.felipegsolutions.codechallenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felipegsolutions.codechallenge.RecyclerViewAdapter
import com.felipegsolutions.codechallenge.databinding.ActivityMainBinding
import com.felipegsolutions.codechallenge.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var adapterPics: RecyclerViewAdapter

    //API KEY 28614023-97bf3e8f3ffa8e57ea87f2b0c
    // https://pixabay.com/api/?key=28614023-97bf3e8f3ffa8e57ea87f2b0c&q=yellow+flowers&image_type=photo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecyclerView()
        initViewModel()

        viewModel.makeApiCAll("fruits")

        binding.button.setOnClickListener {
            val tags = binding.editTextSearch.text.toString().split(",")
            var tagsConcat = ""
            tags.forEach {
                val tag = it.trim()
                tagsConcat += "$tag+"
            }
            tagsConcat = tagsConcat.removeSuffix("+")
            viewModel.makeApiCAll(tagsConcat)
        }

    }

    fun onItemClick() {

    }

    private fun initRecyclerView() {
        adapterPics = RecyclerViewAdapter(this)
        manager = LinearLayoutManager(this)
        binding.recyclerView.apply {
            adapter = adapterPics
            layoutManager = manager
            setHasFixedSize(true)
        }
    }

    private fun initViewModel() {

        viewModel.getLiveDataObserver().observe(this, Observer { pics ->
            if (pics != null) {
                adapterPics.setAdapterData(pics.hits)
                adapterPics.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error getting images", Toast.LENGTH_LONG).show()
            }
        })
    }
}