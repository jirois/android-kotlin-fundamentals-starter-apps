package com.example.roomwordsample

import androidx.lifecycle.LiveData

class WordRepository(private val wordDao: WordDao) {

    // Used the LiveData in order to notify the observer when the data changed

    val allWords : LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word){
        wordDao.insert(word)
    }
}
