package com.bignerdranch.android.geoquiz

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"


class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val questionBank=listOf (
        Question(R.string.question_mario,true),
        Question(R.string.question_Mega_man,true),
        Question(R.string.question_SF,false),
        Question(R.string.question_Sonic,false),
        Question(R.string.question_Pacman,true),
        Question(R.string.question_Zelda,false)
    )
    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)
    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)
    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }
    fun moveToPrevious() {
        currentIndex = (currentIndex - 1) % questionBank.size
    }
    fun scoreCheck(score: Int): Int {
        var displayScore=-1
        if(currentIndex==questionBank.size-1){
            displayScore = (score*100)/questionBank.size
        }
        return displayScore
    }
}