package com.olbareum.olbareum.retrofit.dto.feedback

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.olbareum.olbareum.databinding.ItemFeedbackViewpagerBinding

class FeedbackViewPagerAdapter(
    private val feedbacks: List<String>,
    private val imageUrls: List<String>
) : RecyclerView.Adapter<FeedbackViewPagerAdapter.ImageViewHolder>() {

    class ImageViewHolder(val binding: ItemFeedbackViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemFeedbackViewpagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = feedbacks.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.binding.message.text = feedbacks[position]

        Glide.with(holder.binding.root.context)
            .load(imageUrls[position])
            .into(holder.binding.image)
    }
}