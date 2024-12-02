package com.olbareum.olbareum.select

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olbareum.olbareum.databinding.ItemIntonationTypeRecyclerviewBinding
import com.olbareum.olbareum.enums.FeedbackType

class IntonationTypeRecyclerViewAdapter(
    private val items: List<Pair<String, Int>>
) : RecyclerView.Adapter<IntonationTypeRecyclerViewAdapter.IntonationTypeViewHolder>() {

    inner class IntonationTypeViewHolder(val binding: ItemIntonationTypeRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        const val TYPE_LEFT = 0
        const val TYPE_RIGHT = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntonationTypeViewHolder {
        val binding = ItemIntonationTypeRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return IntonationTypeViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) TYPE_LEFT else TYPE_RIGHT
    }

    override fun onBindViewHolder(holder: IntonationTypeViewHolder, position: Int) {
        val binding = holder.binding
        val context = binding.root.context

        binding.type.text = items[position].first
        binding.image.setImageResource(items[position].second)

        // 마진 설정
        val layoutParams = binding.root.layoutParams as ViewGroup.MarginLayoutParams
        when (getItemViewType(position)) {
            PronunciationTypeRecyclerViewAdapter.TYPE_LEFT -> layoutParams.setMargins(
                dpToPx(context, 20f),
                dpToPx(context, 20f),
                dpToPx(context, 10f),
                dpToPx(context, 20f)
            )

            PronunciationTypeRecyclerViewAdapter.TYPE_RIGHT -> layoutParams.setMargins(
                dpToPx(context, 10f),
                dpToPx(context, 20f),
                dpToPx(context, 20f),
                dpToPx(context, 20f)
            )
        }
        binding.root.layoutParams = layoutParams

        binding.root.setOnClickListener {
            val intent = Intent(context, SentenceSelectActivity::class.java)
            intent.putExtra("feedback_type", FeedbackType.INTONATION)
            intent.putExtra("intonation_type", items[position].first)
            context.startActivity(intent)
        }
    }

    private fun dpToPx(context: Context, dp: Float): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }
}