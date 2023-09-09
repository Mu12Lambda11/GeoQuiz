package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val quizViewModel: QuizViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

        binding.trueButton.setOnClickListener{view: View->
            checkAnswer(true)
            binding.falseButton.isEnabled=false
        }

        binding.falseButton.setOnClickListener{view: View->
            checkAnswer(false)
            binding.trueButton.isEnabled=false
        }

        binding.nextButton.setOnClickListener{
            quizViewModel.moveToNext()
            updateQuestion()
        }
        binding.previousButton.setOnClickListener{
            quizViewModel.moveToPrevious()
            updateQuestion()
        }

        updateQuestion()

    }
    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
        binding.trueButton.isEnabled=true
        binding.falseButton.isEnabled=true
    }
    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer=quizViewModel.currentQuestionAnswer

        val messageResId= if (userAnswer== correctAnswer){
            R.string.correct_toast
        } else{
            R.string.incorrect_toast
        }
        //variable to track score
        var messageScore = 0
        if (userAnswer== correctAnswer){
            messageScore+=1
        }
        Toast.makeText(this, messageResId,  Toast.LENGTH_SHORT).show()
    }
    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
}
