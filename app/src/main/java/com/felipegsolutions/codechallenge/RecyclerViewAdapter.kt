package com.felipegsolutions.codechallenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.felipegsolutions.codechallenge.model.Hit
import com.felipegsolutions.codechallenge.databinding.ViewItemBinding
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var data: List<Hit>? = null

    public fun setAdapterData(data: List<Hit>) {
        this.data = data
    }

    inner class MyViewHolder(private val listItemBinding: ViewItemBinding) : RecyclerView.ViewHolder(listItemBinding.root) {
        fun bind(item: Hit) {
            listItemBinding.hitList = item
            Picasso.get().load(item.previewURL).into(listItemBinding.previewImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ViewItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        data?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        if (data == null) {
            return 0
        }
        return data?.size!!
    }
}