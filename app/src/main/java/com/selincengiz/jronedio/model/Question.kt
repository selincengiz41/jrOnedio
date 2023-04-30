package com.selincengiz.jronedio.model

data class Question(
    val multiChoiceAnswers: List<MultiChoiceAnswer>,
    val question: String
)