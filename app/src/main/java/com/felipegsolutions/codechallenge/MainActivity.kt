package com.felipegsolutions.codechallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felipegsolutions.codechallenge.databinding.ActivityMainBinding
import com.felipegsolutions.codechallenge.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
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
                tagsConcat += "$it+"
            }
            tagsConcat = tagsConcat.removeSuffix("+")
            viewModel.makeApiCAll(tagsConcat)
        }

    }

    private fun initRecyclerView() {
        adapterPics = RecyclerViewAdapter()
        manager = LinearLayoutManager(this)
        binding.recyclerView.apply {
            adapter = adapterPics
            layoutManager = manager
            setHasFixedSize(true)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
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