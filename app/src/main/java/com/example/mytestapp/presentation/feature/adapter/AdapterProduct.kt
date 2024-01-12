package com.example.mytestapp.presentation.feature.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.data.model.DataItemProduct
import com.example.mytestapp.databinding.ItemProductBinding


class AdapterProduct(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onSelectItem: ((List<Int>?) -> Unit)? = null
    var data: List<DataItemProduct> = arrayListOf()

    fun loadData(content: List<DataItemProduct>) {
        this.data = content
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemProductBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val item = data[holder.bindingAdapterPosition]
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItemProduct) = with(binding) {
            tvPrice.text = data.titlePrice
            subPrice.text = data.subPrice
            tvName.text = data.name
            tvStatus.text = data.status
            tvTimer.text = data.timer
            progressBar.progress = data.review ?: 0
            if (bindingAdapterPosition % 2 != 0) {
                itemCard.setBackgroundResource(R.drawable.bg_card_view_red)
                tvStatus.setTextColor(ContextCompat.getColor(context, R.color.bgApp))
            } else {
                itemCard.setBackgroundResource(R.drawable.bg_crad_view_green)
                tvStatus.setTextColor(ContextCompat.getColor(context, R.color.cool_dark_green))
            }
        }
    }


}