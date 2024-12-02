package com.olbareum.olbareum.select

import android.content.Context
import android.content.Intent
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.olbareum.olbareum.R
import com.olbareum.olbareum.databinding.ItemPronunciationTypeRecyclerviewBinding
import com.olbareum.olbareum.enums.FeedbackType
import com.olbareum.olbareum.util.CustomTypefaceSpan

class PronunciationTypeRecyclerViewAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<PronunciationTypeRecyclerViewAdapter.PronunciationTypeViewHolder>() {

    inner class PronunciationTypeViewHolder(val binding: ItemPronunciationTypeRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        const val TYPE_LEFT = 0
        const val TYPE_RIGHT = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PronunciationTypeViewHolder {
        val binding = ItemPronunciationTypeRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PronunciationTypeViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) TYPE_LEFT else TYPE_RIGHT
    }

    override fun onBindViewHolder(holder: PronunciationTypeViewHolder, position: Int) {
        val binding = holder.binding
        val context = binding.root.context

        val text = items[position]
        val index = text.indexOf('(')

        if (index != -1) {
            // '(' 문자의 위치부터 끝까지 글씨 스타일 변경
            val spannable = SpannableString(text)

            val typeface = ResourcesCompat.getFont(context, R.font.pretendard_regular)
            typeface?.let {
                spannable.setSpan(
                    CustomTypefaceSpan("", it),
                    index,
                    text.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            spannable.setSpan(
                AbsoluteSizeSpan(dpToPx(context, 16f), false),
                index,
                text.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            binding.type.text = spannable
        } else {
            binding.type.text = text
        }

        // 마진 설정
        val layoutParams = binding.root.layoutParams as ViewGroup.MarginLayoutParams
        when (getItemViewType(position)) {
            TYPE_LEFT -> layoutParams.setMargins(
                dpToPx(context, 20f),
                dpToPx(context, 10f),
                dpToPx(context, 10f),
                dpToPx(context, 10f)
            )

            TYPE_RIGHT -> layoutParams.setMargins(
                dpToPx(context, 10f),
                dpToPx(context, 10f),
                dpToPx(context, 20f),
                dpToPx(context, 10f)
            )
        }
        binding.root.layoutParams = layoutParams

        binding.root.setOnClickListener {
            val intent = Intent(context, SentenceSelectActivity::class.java)
            intent.putExtra("feedback_type", FeedbackType.PRONUNCIATION)
            intent.putExtra("pronunciation_type", items[position])
            context.startActivity(intent)
        }
    }

    private fun dpToPx(context: Context, dp: Float): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }
}