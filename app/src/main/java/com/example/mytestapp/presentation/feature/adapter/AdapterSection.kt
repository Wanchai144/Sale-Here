package com.example.mytestapp.presentation.feature.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import com.example.mytestapp.R
import com.example.mytestapp.data.model.DataBannerOffer
import com.example.mytestapp.data.model.DataBannerSuggest
import com.example.mytestapp.data.model.DataItemProduct
import com.example.mytestapp.databinding.ItemBannerBinding
import com.example.mytestapp.databinding.ItemBannerSuggustBinding
import com.example.mytestapp.databinding.ItemProductBinding

class AdapterSection(var context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_BANNER_OFFER = 1
        const val VIEW_TYPE_BANNER_SUGGEST_FOR_YOU = 2
    }

    var data: List<Any> = listOf()


    fun loadData(content: List<Any>) {
        this.data = content
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_BANNER_OFFER -> {
                val view = ItemBannerBinding.inflate(inflater, parent, false)
                ViewHolder(view)
            }
            VIEW_TYPE_BANNER_SUGGEST_FOR_YOU -> {
                val view = ItemBannerSuggustBinding.inflate(inflater, parent, false)
                ViewHolderSuggest(view)
            }
            else -> {
                val view = ItemBannerBinding.inflate(inflater, parent, false)
                ViewHolder(view)
            }
        }
    }


    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val item = data[holder.bindingAdapterPosition] as DataBannerOffer
            holder.bind(item)
        }

        if (holder is ViewHolderSuggest) {
            val item = data[holder.bindingAdapterPosition] as DataBannerSuggest
            holder.bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is DataBannerOffer -> VIEW_TYPE_BANNER_OFFER
            is DataBannerSuggest -> VIEW_TYPE_BANNER_SUGGEST_FOR_YOU
            else -> super.getItemViewType(position)
        }
    }


    inner class ViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataBannerOffer) = with(binding) {
            val adapters = AdapterBannerOffer(context)
            adapters.loadData(data.image?: listOf())
            val transformer = CompositePageTransformer()
            transformer.addTransformer { page, position ->
                val r = 1 - kotlin.math.abs(position)
                page.scaleY = 0.85f + r * 0.14f
            }
            rvBannerOffer.setPageTransformer(transformer)
            rvBannerOffer.apply {
                adapter = adapters
                offscreenPageLimit = 3
                clipToPadding = false
                clipChildren = false
                getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            }
        }
    }

    inner class ViewHolderSuggest(private val binding: ItemBannerSuggustBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data:DataBannerSuggest) = with(binding) {
            val adapters = AdapterBannerSuggest(context)
            adapters.loadData(data.image?: listOf())
            rvBannerSuggest.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            rvBannerSuggest.adapter = adapters
        }
    }
}