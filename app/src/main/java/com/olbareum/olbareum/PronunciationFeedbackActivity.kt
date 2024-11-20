package com.olbareum.olbareum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.olbareum.olbareum.databinding.ActivityPronunciationFeedbackBinding
import com.olbareum.olbareum.retrofit.dto.feedback.FeedbackStatus
import com.olbareum.olbareum.retrofit.dto.feedback.FeedbackViewPagerAdapter
import com.olbareum.olbareum.retrofit.dto.feedback.PronunciationFeedbackResponseDto

class PronunciationFeedbackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPronunciationFeedbackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPronunciationFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val feedbacks = listOf(
            "입을 동그랗게 모으는 대신, 'ㅔ' 발음을 위해 입을 옆으로 살짝 벌려주세요. 혀는 'ㅗ'에서처럼 뒤로 당기지 말고, 'ㅔ' 발음 시에는 혀를 중간 정도로 평평하게 두세요. 'ㅗ'는 입술을 둥글게 모으고 혀를 뒤로 당기는 반면, 'ㅔ'는 입을 옆으로 벌리고 혀를 더 앞으로 두는 것이 핵심 차이입니다.",
            "입술을 앞으로 내미는 대신, 입을 새끼손가락 두 개만큼 벌리고 편안하게 유지하세요. 혀는 'ㅠ'에서처럼 뒤로 당기지 말고, 입 안에서 자연스럽게 중간 정도에 위치하도록 하세요. 'ㅠ'는 입술을 동그랗게 모으는 반면, 'ㅕ'는 입술을 더 편안하게 벌리고 발음하는 것이 핵심입니다.",
            "'ㄷ' 발음은 혀끝을 윗니 뒤쪽 잇몸에 붙이고 공기를 천천히 내보내지만, 'ㅌ' 발음은 같은 위치에서 혀를 떼면서 공기를 더 세게 내보내야 합니다. 'ㄷ'에서 'ㅌ'로 개선하려면 혀 위치는 그대로 유지하되, 발음할 때 공기를 더 강하게 밀어내는 연습이 필요합니다.",
            "입을 더 크게 벌려서 새끼손가락 세 개가 들어갈 정도로 하세요. 혀는 입 안에서 최대한 아래로 내리고, 아랫니 뒤쪽에 가깝게 두세요. 'ㅗ'는 입술을 동그랗게 모으고 혀의 뒤쪽을 올리지만, 'ㅏ'는 입을 넓게 벌리고 혀를 아래로 내리는 것이 핵심 차이입니다."
        )
        val imageUrls = listOf(
            "https://allbareum.s3.ap-northeast-2.amazonaws.com/images/feedback/ㅗ_ㅔ.jpg",
            "https://allbareum.s3.ap-northeast-2.amazonaws.com/images/feedback/ㅠ_ㅕ.jpg",
            "https://allbareum.s3.ap-northeast-2.amazonaws.com/images/feedback/ㄷ_ㅌ.jpg",
            "https://allbareum.s3.ap-northeast-2.amazonaws.com/images/feedback/ㅗ_ㅏ.jpg"
        )

        binding.feedbackViewPager.adapter = FeedbackViewPagerAdapter(feedbacks, imageUrls)


        val feedbackData =
            intent.getParcelableExtra<PronunciationFeedbackResponseDto>("feedbackData")
        if (feedbackData != null) {

            when (FeedbackStatus.fromValue(feedbackData.status)) {
                FeedbackStatus.CORRECT -> {}// TODO: 정확한 발음 (피드백&입모양 사진 없음)
                FeedbackStatus.INCORRECT -> {}// 발음에 틀린 부분 있음 (피드백&입모양 사진 있음)
            }

            val viewPager = binding.feedbackViewPager
            val adapter = FeedbackViewPagerAdapter(
                feedbackData.pronunciationFeedbacks,
                feedbackData.feedbackImageUrls
            )
            viewPager.adapter = adapter


//            val pronunciationScore = feedbackData.pronunciationScore.toInt()
//            binding.score.text = "${pronunciationScore}점"
//            binding.scoreBar.progress = pronunciationScore
//            binding.pronunciationFeedback.text = feedbackData.pronunciationFeedback
//            binding.sentence.text = feedbackData.textSentence
//            binding.transcription.text = feedbackData.transcription
//
//            Glide.with(binding.root.context)
//                .load(feedbackData.pronunciationFeedbackImage)
//                .into(binding.pronunciationFeedbackImage)
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}