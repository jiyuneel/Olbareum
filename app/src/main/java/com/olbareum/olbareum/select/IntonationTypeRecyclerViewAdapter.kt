package com.olbareum.olbareum.select

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olbareum.olbareum.databinding.ItemIntonationTypeRecyclerviewBinding

class IntonationTypeRecyclerViewAdapter(
    private val items: List<Pair<String, Int>>
) : RecyclerView.Adapter<IntonationTypeRecyclerViewAdapter.IntonationTypeViewHolder>() {
    inner class IntonationTypeViewHolder(val binding: ItemIntonationTypeRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntonationTypeViewHolder {
        val binding = ItemIntonationTypeRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return IntonationTypeViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: IntonationTypeViewHolder, position: Int) {
        val binding = holder.binding

        binding.type.text = items[position].first
        binding.image.setImageResource(items[position].second)
    }
}