package com.selincengiz.jronedio.model

import java.io.Serializable

data class Test(
    val header: Header,
    val questions: List<Question>,
   // val quizIdentity: String,
    val quizType: String,
    val resultBuckets: List<ResultBucket>
):Serializable