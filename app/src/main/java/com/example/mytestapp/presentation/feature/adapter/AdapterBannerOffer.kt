package com.example.mytestapp.presentation.feature.adapter

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mytestapp.R
import com.example.mytestapp.data.model.DataBannerOffer
import com.example.mytestapp.data.model.DataBannerSuggest
import com.example.mytestapp.databinding.ItemImageBinding

class AdapterBannerOffer (var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: List<Int> = arrayListOf()

    fun loadData(content: List<Int>) {
        this.data = content
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemImageBinding.inflate(inflater, parent, false)
        view.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val item = data[holder.bindingAdapterPosition]
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Int) = with(binding) {
            Glide.with(context)
                .load(data)
                .into(imageView)
        }
    }
}