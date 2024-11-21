package com.olbareum.olbareum.select

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olbareum.olbareum.databinding.ItemSentenceRecyclerviewBinding
import com.olbareum.olbareum.record.RecordActivity

class SentenceRecyclerViewAdapter(
    private val items: List<Int>
) : RecyclerView.Adapter<SentenceRecyclerViewAdapter.SentenceViewHolder>() {

    class SentenceViewHolder(val binding: ItemSentenceRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SentenceViewHolder {
        return SentenceViewHolder(
            ItemSentenceRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SentenceViewHolder, position: Int) {
        val binding = holder.binding
        val context = binding.root.context

        binding.level.text = items[position].toString()

        binding.button.setOnClickListener {
            context.startActivity(Intent(context, RecordActivity::class.java))
        }
    }
}