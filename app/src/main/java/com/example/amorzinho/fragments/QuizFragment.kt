package com.example.amorzinho.fragments

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.amorzinho.R
import com.example.amorzinho.models.Question

class QuizFragment : Fragment() {

    private lateinit var quizContainer: View
    private lateinit var questionText: TextView
    private lateinit var optionsGroup: RadioGroup
    private lateinit var feedbackText: TextView
    private lateinit var nextButton: Button
    private lateinit var progressText: TextView

    private val questions = mutableListOf<Question>()
    private var currentQuestionIndex = 0
    private var score = 0
    private var answerSelected = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar views
        quizContainer = view.findViewById(R.id.quizContainer)
        questionText = view.findViewById(R.id.questionText)
        optionsGroup = view.findViewById(R.id.optionsGroup)
        feedbackText = view.findViewById(R.id.feedbackText)
        nextButton = view.findViewById(R.id.nextButton)
        progressText = view.findViewById(R.id.progressText)

        // Configurar perguntas
        setupQuestions()

        // Mostrar primeira pergunta
        showQuestion(questions[currentQuestionIndex])

        // Seleção de resposta
        optionsGroup.setOnCheckedChangeListener { group, checkedId ->
            if (!answerSelected) {
                val selectedIndex = when (checkedId) {
                    R.id.option1 -> 0
                    R.id.option2 -> 1
                    R.id.option3 -> 2
                    else -> -1
                }

                if (selectedIndex != -1) {
                    answerSelected = true
                    val currentQuestion = questions[currentQuestionIndex]
                    checkAnswer(selectedIndex, currentQuestion.correctAnswerIndex)
                }
            }
        }

        // Botão de próxima pergunta
        nextButton.setOnClickListener {
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                optionsGroup.clearCheck()
                answerSelected = false
                feedbackText.visibility = View.INVISIBLE
                nextButton.visibility = View.INVISIBLE
                showQuestion(questions[currentQuestionIndex])
            } else {
                showFinalScore()
            }
        }
    }

    private fun setupQuestions() {
        val questionsArray = resources.getStringArray(R.array.quiz_questions)
        val optionsList = listOf(
            resources.getStringArray(R.array.quiz_options_1),
            resources.getStringArray(R.array.quiz_options_2),
            resources.getStringArray(R.array.quiz_options_3),
            resources.getStringArray(R.array.quiz_options_4)
        )

        val correctAnswers = listOf(2, 0, 1, 2)

        for (i in questionsArray.indices) {
            questions.add(
                Question(
                    text = questionsArray[i],
                    options = optionsList[i].toList(),
                    correctAnswerIndex = correctAnswers[i]
                )
            )
        }
    }

    private fun showQuestion(question: Question) {
        progressText.text = "${currentQuestionIndex + 1}/${questions.size}"
        questionText.text = question.text
        (optionsGroup.getChildAt(0) as RadioButton).text = question.options[0]
        (optionsGroup.getChildAt(1) as RadioButton).text = question.options[1]
        (optionsGroup.getChildAt(2) as RadioButton).text = question.options[2]
        quizContainer.setBackgroundColor(Color.WHITE)
        feedbackText.visibility = View.INVISIBLE
        nextButton.visibility = View.INVISIBLE
        optionsGroup.isEnabled = true
    }

    private fun checkAnswer(selectedIndex: Int, correctIndex: Int) {
        val isCorrect = selectedIndex == correctIndex

        val correctMessages = resources.getStringArray(R.array.correct_messages)
        val wrongMessages = resources.getStringArray(R.array.wrong_messages)

        val bgColor = try {
            if (isCorrect) {
                ContextCompat.getColor(requireContext(), R.color.correct_green)
            } else {
                ContextCompat.getColor(requireContext(), R.color.wrong_red)
            }
        } catch (e: Exception) {
            if (isCorrect) Color.GREEN else Color.RED
        }

        quizContainer.setBackgroundColor(bgColor)
        feedbackText.text = if (isCorrect) {
            correctMessages.random()
        } else {
            wrongMessages.random()
        }
        feedbackText.visibility = View.VISIBLE

        if (isCorrect) score++
        optionsGroup.isEnabled = false

        Handler(Looper.getMainLooper()).postDelayed({
            nextButton.visibility = View.VISIBLE
        }, 1500)
    }

    private fun showFinalScore() {
        val inflater = layoutInflater
        val resultView = inflater.inflate(R.layout.quiz_result, null)

        val container = view?.findViewById<ViewGroup>(R.id.quizContainer)
        container?.removeAllViews()
        container?.addView(resultView)

        val resultText = resultView.findViewById<TextView>(R.id.resultText)
        val messageText = resultView.findViewById<TextView>(R.id.messageText)

        resultText.text = "$score/${questions.size}"

        val percentage = (score.toFloat() / questions.size) * 100
        messageText.text = when {
            percentage > 90 -> "Sabia que ia acertar ❤️"
            percentage > 70 -> "Umazinha eu deixo passar"
            percentage > 50 -> "Quase que reprovou em, fica esperto! 😉"
            else -> "EU VOU TE BATEEER!!!!"
        }

        resultView.findViewById<Button>(R.id.playAgainButton).setOnClickListener {
            currentQuestionIndex = 0
            score = 0
            container?.removeAllViews()
            container?.addView(view)
            showQuestion(questions[currentQuestionIndex])
        }
    }
}