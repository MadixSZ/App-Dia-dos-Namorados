package com.example.amorzinho.models

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)