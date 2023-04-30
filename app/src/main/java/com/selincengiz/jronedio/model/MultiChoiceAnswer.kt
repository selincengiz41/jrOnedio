package com.selincengiz.jronedio.model

data class MultiChoiceAnswer(
    val answer: String,
    val buckets: List<String>
)