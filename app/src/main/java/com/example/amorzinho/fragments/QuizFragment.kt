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

    private lateinit var quizContent: View
    private lateinit var resultContent: View
    private lateinit var questionText: TextView
    private lateinit var optionsGroup: RadioGroup
    private lateinit var feedbackText: TextView
    private lateinit var nextButton: Button
    private lateinit var progressText: TextView
    private lateinit var resultText: TextView
    private lateinit var messageText: TextView
    private lateinit var playAgainButton: Button
    private lateinit var quizContainer: View

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
        quizContent = view.findViewById(R.id.quizContent)
        resultContent = view.findViewById(R.id.resultContent)
        questionText = view.findViewById(R.id.questionText)
        optionsGroup = view.findViewById(R.id.optionsGroup)
        feedbackText = view.findViewById(R.id.feedbackText)
        nextButton = view.findViewById(R.id.nextButton)
        progressText = view.findViewById(R.id.progressText)
        resultText = view.findViewById(R.id.resultText)
        messageText = view.findViewById(R.id.messageText)
        playAgainButton = view.findViewById(R.id.playAgainButton)

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
                resetQuestionState()
                showQuestion(questions[currentQuestionIndex])
            } else {
                showFinalScore()
            }
        }

        // Botão para jogar novamente
        playAgainButton.setOnClickListener {
            restartQuiz()
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

        // Resetar texto das opções
        (optionsGroup.getChildAt(0) as RadioButton).text = question.options[0]
        (optionsGroup.getChildAt(1) as RadioButton).text = question.options[1]
        (optionsGroup.getChildAt(2) as RadioButton).text = question.options[2]

        // Restaurar aparência padrão
        optionsGroup.isEnabled = true
        optionsGroup.clearCheck()
        setDefaultBackground()
        feedbackText.visibility = View.INVISIBLE
        nextButton.visibility = View.INVISIBLE
        answerSelected = false
    }

    private fun resetQuestionState() {
        optionsGroup.clearCheck()
        answerSelected = false
        feedbackText.visibility = View.INVISIBLE
        nextButton.visibility = View.INVISIBLE
        setDefaultBackground()
    }

    private fun setDefaultBackground() {
        try {
            // Usar recurso drawable diretamente
            quizContainer.setBackgroundResource(R.drawable.fundoquiz)
        } catch (e: Exception) {
            // Fallback para cor sólida
            quizContainer.setBackgroundColor(
                ContextCompat.getColor(requireContext(), R.color.purple_200)
            )
            Log.e("QuizFragment", "Erro ao carregar fundo padrão", e)
        }
    }

    private fun checkAnswer(selectedIndex: Int, correctIndex: Int) {
        val isCorrect = selectedIndex == correctIndex

        val correctMessages = resources.getStringArray(R.array.correct_messages)
        val wrongMessages = resources.getStringArray(R.array.wrong_messages)

        // Definir imagem de fundo usando recursos drawable
        try {
            val backgroundRes = if (isCorrect) {
                R.drawable.fundo_acerto // Imagem para acerto
            } else {
                R.drawable.fundo_erro   // Imagem para erro
            }

            quizContainer.setBackgroundResource(backgroundRes)
        } catch (e: Exception) {
            // Fallback para cores sólidas
            val bgColor = if (isCorrect) {
                ContextCompat.getColor(requireContext(), R.color.correct_green)
            } else {
                ContextCompat.getColor(requireContext(), R.color.wrong_red)
            }

            quizContainer.setBackgroundColor(bgColor)
            Log.e("QuizFragment", "Erro ao carregar fundo de resposta", e)
        }

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
        quizContent.visibility = View.GONE
        resultContent.visibility = View.VISIBLE

        resultText.text = "$score/${questions.size}"

        val percentage = (score.toFloat() / questions.size) * 100
        messageText.text = when {
            percentage > 90 -> "Sabia que ia acertar ❤️"
            percentage > 70 -> "Umazinha eu deixo passar"
            percentage > 50 -> "Quase que reprovou em, fica esperto! 😉"
            else -> "EU VOU TE BATEEER!!!!"
        }

        // Restaurar fundo padrão para o resultado
        setDefaultBackground()
    }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0

        quizContent.visibility = View.VISIBLE
        resultContent.visibility = View.GONE

        resetQuestionState()
        showQuestion(questions[currentQuestionIndex])
    }
}