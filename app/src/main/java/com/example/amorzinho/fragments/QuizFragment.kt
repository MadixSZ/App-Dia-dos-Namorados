package com.example.amorzinho.fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import com.example.amorzinho.R

class QuizFragment : Fragment(R.layout.activity_fragment_quiz) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questions = resources.getStringArray(R.array.quiz_questions)
        val answers = resources.getStringArray(R.array.quiz_answers)

        // Implemente sua lógica de quiz aqui
    }
}