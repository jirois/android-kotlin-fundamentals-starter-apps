package com.example.roomwordsample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WordViewModel(application: Application):AndroidViewModel(application){

    //The ViewModel maintains a reference to the repository to get the data
    private val repository: WordRepository

    // LiveData gives us updated words when they change
    val allWords: LiveData<List<Word>>

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application).wordDao()

        repository = WordRepository(wordsDao)

        allWords = repository.allWords
    }

    fun insert(word: Word) = viewModelScope.launch{
      repository.insert(word)
    }

}