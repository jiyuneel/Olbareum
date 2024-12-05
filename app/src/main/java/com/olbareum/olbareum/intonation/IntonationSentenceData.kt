package com.olbareum.olbareum.intonation

object IntonationSentenceData {
    val data: Map<String, List<Pair<String, String>>> = mapOf(
        "의문문" to listOf(
            Pair("0_0", "요즘도 바빠?"),
            Pair("0_1", "밥 먹었어?"),
            Pair("0_2", "어디 가는 길이야?"),
            Pair("0_3", "오늘 날씨 어때?"),
            Pair("0_4", "지금 갈거야?"),
            Pair("0_5", "오래 기다렸어?"),
            Pair("0_6", "운동 좋아하세요?"),
            Pair("0_7", "지금 뭐해?"),
            Pair("0_8", "새로운 소식 있어?"),
            Pair("0_9", "주말에 뭐 할거야?")
        ),
        "평서문" to listOf(
            Pair("1_0", "요즘도 바빠."),
            Pair("1_1", "밥 먹었어."),
            Pair("1_2", "지금 갈거야."),
            Pair("1_3", "오래 기다렸어."),
            Pair("1_4", "운동 좋아해요."),
            Pair("1_5", "새로운 소식 있어."),
            Pair("1_6", "주말에 쉴 거에요."),
            Pair("1_7", "오늘은 비가 와요."),
            Pair("1_8", "집에 가고 있어요."),
            Pair("1_9", "다음주에 약속 있어.")
        ),
        "감탄문" to listOf(
            Pair("2_0", "정말 축하해!"),
            Pair("2_1", "완전 잘했네!"),
            Pair("2_2", "진짜 대단하다!"),
            Pair("2_3", "너 너무 멋있어!"),
            Pair("2_4", "이거 진짜 끝내준다!"),
            Pair("2_5", "진짜 감동이야!"),
            Pair("2_6", "이런 건 처음 봐!"),
            Pair("2_7", "완전 영화 같아!"),
            Pair("2_8", "시험 잘봤구나!"),
            Pair("2_9", "장관이다 정말!")
        ),
        "청유문" to listOf(
            Pair("3_0", "우리 내일 만나자."),
            Pair("3_1", "같이 가요."),
            Pair("3_2", "우선 이거로 정하시죠."),
            Pair("3_3", "다음엔 거기 가지 말자."),
            Pair("3_4", "조금만 더 기다려 봐요."),
            Pair("3_5", "그냥 걸어가자."),
            Pair("3_6", "이 일은 나중에 하자."),
            Pair("3_7", "커피 한잔 해요."),
            Pair("3_8", "지금 바로 시작하자."),
            Pair("3_9", "주말에 등산가자.")
        )
    )

    // 문장 -> 코드 맵
    private val sentenceToCodeMap: Map<String, String> = data.values.flatten().associate { it.second to it.first }

    // 코드 -> 문장 맵
    private val codeToSentenceMap: Map<String, String> = data.values.flatten().associate { it.first to it.second }


    // 주어진 문장에 해당하는 코드를 반환
    fun getCodeBySentence(sentence: String): String {
        return sentenceToCodeMap[sentence] ?: ""
    }

    // 주어진 코드에 해당하는 문장을 반환
    fun getSentenceByCode(code: String): String {
        return codeToSentenceMap[code] ?: ""
    }
}