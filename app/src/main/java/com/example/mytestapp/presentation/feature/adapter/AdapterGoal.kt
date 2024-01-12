package com.example.mytestapp.presentation.feature.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.data.model.DataItemGrid
import com.example.mytestapp.databinding.ItemAchievementBinding

class AdapterGoal  (var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: List<DataItemGrid> = arrayListOf()

    fun loadData(content: List<DataItemGrid>) {
        this.data = content
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemAchievementBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val item = data[holder.bindingAdapterPosition]
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemAchievementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItemGrid) = with(binding) {
            tvName.text = data.title
            itemCard.setBackgroundResource(R.drawable.bg_item_goal)
        }
    }


}