package com.olbareum.olbareum.feedback

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.olbareum.olbareum.databinding.ItemFeedbackViewpagerBinding

class FeedbackViewPagerAdapter(
    private val feedbackCount: Int,
    private val feedbacks: List<String>,
    private val imageUrls: List<String>
) : RecyclerView.Adapter<FeedbackViewPagerAdapter.FeedbackViewHolder>() {

    class FeedbackViewHolder(val binding: ItemFeedbackViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackViewHolder {
        return FeedbackViewHolder(
            ItemFeedbackViewpagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = feedbackCount

    override fun onBindViewHolder(holder: FeedbackViewHolder, position: Int) {
        holder.binding.message.text = feedbacks[position]

        Glide.with(holder.binding.root.context)
            .load(imageUrls[position])
            .into(holder.binding.image)
    }
}