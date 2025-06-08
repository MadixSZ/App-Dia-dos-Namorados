package com.example.amorzinho.fragments

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup // Importação necessária adicionada
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.amorzinho.R
import com.example.amorzinho.models.Question

class QuizFragment : Fragment(R.layout.activity_fragment_quiz) {

    private lateinit var quizContainer: View
    private lateinit var questionText: TextView
    private lateinit var optionsGroup: RadioGroup
    private lateinit var feedbackText: TextView
    private lateinit var nextButton: TextView
    private lateinit var progressText: TextView

    private val questions = mutableListOf<Question>()
    private var currentQuestionIndex = 0
    private var score = 0

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

        // Listener para seleção de resposta
        optionsGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedOption = view.findViewById<RadioButton>(checkedId)
            val selectedIndex = when (checkedId) {
                R.id.option1 -> 0
                R.id.option2 -> 1
                R.id.option3 -> 2
                else -> -1
            }

            if (selectedIndex != -1) {
                val currentQuestion = questions[currentQuestionIndex]
                checkAnswer(selectedIndex, currentQuestion.correctAnswerIndex)
            }
        }

        // Listener para botão de próxima pergunta
        nextButton.setOnClickListener {
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                optionsGroup.clearCheck()
                feedbackText.visibility = View.INVISIBLE
                nextButton.visibility = View.INVISIBLE
                showQuestion(questions[currentQuestionIndex])
            } else {
                // Fim do quiz
                showFinalScore()
            }
        }
    }

    private fun setupQuestions() {
        // Obter perguntas e opções dos recursos
        val questionsArray = resources.getStringArray(R.array.quiz_questions)
        val optionsList = listOf(
            resources.getStringArray(R.array.quiz_options_1),
            resources.getStringArray(R.array.quiz_options_2),
            resources.getStringArray(R.array.quiz_options_3),
            resources.getStringArray(R.array.quiz_options_4)
        )

        // Criar objetos Question
        for (i in questionsArray.indices) {
            questions.add(
                Question(
                    text = questionsArray[i],
                    options = optionsList[i].toList(),
                    correctAnswerIndex = 0 // Primeira opção é a correta
                )
            )
        }
    }

    private fun showQuestion(question: Question) {
        // Atualizar progresso
        progressText.text = "${currentQuestionIndex + 1}/${questions.size}"

        // Mostrar pergunta
        questionText.text = question.text

        // Mostrar opções
        (optionsGroup.getChildAt(0) as RadioButton).text = question.options[0]
        (optionsGroup.getChildAt(1) as RadioButton).text = question.options[1]
        (optionsGroup.getChildAt(2) as RadioButton).text = question.options[2]

        // Resetar UI
        quizContainer.setBackgroundColor(Color.WHITE)
        feedbackText.visibility = View.INVISIBLE
        nextButton.visibility = View.INVISIBLE
    }

    private fun checkAnswer(selectedIndex: Int, correctIndex: Int) {
        val isCorrect = selectedIndex == correctIndex

        // Obter mensagens aleatórias
        val correctMessages = resources.getStringArray(R.array.correct_messages)
        val wrongMessages = resources.getStringArray(R.array.wrong_messages)

        // Mudar cor de fundo
        val bgColor = if (isCorrect) {
            ContextCompat.getColor(requireContext(), R.color.correct_green)
        } else {
            ContextCompat.getColor(requireContext(), R.color.wrong_red)
        }
        quizContainer.setBackgroundColor(bgColor)

        // Mostrar feedback
        feedbackText.text = if (isCorrect) {
            correctMessages.random()
        } else {
            wrongMessages.random()
        }
        feedbackText.visibility = View.VISIBLE

        // Aumentar pontuação se correto
        if (isCorrect) score++

        // Desabilitar seleção de novas respostas
        optionsGroup.setOnCheckedChangeListener(null)

        // Mostrar botão de próxima pergunta após 1.5 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            nextButton.visibility = View.VISIBLE
        }, 1500)
    }

    private fun showFinalScore() {
        // Substituir todo o conteúdo pela tela de resultados
        val inflater = layoutInflater
        val resultView = inflater.inflate(R.layout.quiz_result, null)

        // CORREÇÃO: Usando ViewGroup para acessar o container
        val container = view?.findViewById<ViewGroup>(R.id.quizContainer)
        container?.removeAllViews()
        container?.addView(resultView)

        // Configurar resultado
        val resultText = resultView.findViewById<TextView>(R.id.resultText)
        val messageText = resultView.findViewById<TextView>(R.id.messageText)

        resultText.text = "$score/${questions.size}"

        val percentage = (score.toFloat() / questions.size) * 100
        messageText.text = when {
            percentage > 90 -> "Nossa! Você me conhece perfeitamente! ❤️"
            percentage > 70 -> "Quase perfeito! Você me conhece muito bem! 😍"
            percentage > 50 -> "Bom! Mas ainda podemos nos conhecer melhor! 😊"
            else -> "Precisamos passar mais tempo juntos! 😉"
        }

        // Botão para jogar novamente
        resultView.findViewById<TextView>(R.id.playAgainButton).setOnClickListener {
            // Reiniciar o quiz
            currentQuestionIndex = 0
            score = 0
            container?.removeAllViews()
            container?.addView(view) // Adiciona a view original de volta
            showQuestion(questions[currentQuestionIndex])
        }
    }
}