package com.olbareum.olbareum.select

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olbareum.olbareum.databinding.ItemSentenceRecyclerviewBinding
import com.olbareum.olbareum.enums.FeedbackType
import com.olbareum.olbareum.record.RecordActivity

class SentenceRecyclerViewAdapter(
    private val items: List<String>,
    private val feedbackType: FeedbackType?,
) : RecyclerView.Adapter<SentenceRecyclerViewAdapter.SentenceViewHolder>() {

    inner class SentenceViewHolder(val binding: ItemSentenceRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SentenceViewHolder {
        val binding = ItemSentenceRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SentenceViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SentenceViewHolder, position: Int) {
        val binding = holder.binding
        val context = binding.root.context

        binding.sentence.text = items[position]

        binding.root.setOnClickListener {
            val intent = Intent(context, RecordActivity::class.java)
            intent.putExtra("feedback_type", feedbackType)
            intent.putExtra("sentence", items[position])
            context.startActivity(intent)
        }
    }
}