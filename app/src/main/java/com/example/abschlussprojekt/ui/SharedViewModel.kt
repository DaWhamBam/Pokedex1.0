package com.example.abschlussprojekt.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.abschlussprojekt.data.models.Pokemon

class SharedViewModel(application: Application) : AndroidViewModel(application) {

   // private val database = getDatabase(application)

    var inputText = MutableLiveData<String>()

    lateinit var currentPokemon: Pokemon

}