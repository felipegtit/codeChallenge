package com.felipegsolutions.codechallenge

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.felipegsolutions.codechallenge.model.Hit
import com.felipegsolutions.codechallenge.databinding.ViewItemBinding
import com.felipegsolutions.codechallenge.presentation.DetailsActivity
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

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
        holder.itemView.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Are you sure you want to open the details for this item?")
            dialog.setPositiveButton("YES", DialogInterface.OnClickListener { dialogInterface, i ->
                val intent = Intent(context, DetailsActivity::class.java).apply {
                    putExtra("position", position)
                }
                context.startActivity(intent)
            })
            dialog.setNegativeButton("NO", DialogInterface.OnClickListener { dialogInterface, i ->

            })
            dialog.show()
        }
    }

    override fun getItemCount(): Int {
        if (data == null) {
            return 0
        }
        return data?.size!!
    }
}