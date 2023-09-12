package com.example.abschlussprojekt.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.abschlussprojekt.data.local.getDatabase
import com.example.abschlussprojekt.data.models.pokemon.Pokemon
import com.example.abschlussprojekt.data.remote.AppRepository
import com.example.abschlussprojekt.data.remote.PokeApi
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)

    var inputText = MutableLiveData<String>()

    lateinit var currentPokemon: Pokemon

    private val repository = AppRepository(PokeApi, database)
    val pokeList = repository.pokeList

    val pokeName = repository.pokemon.value?.name

    val pokemon = repository.pokemon.value

    init {
        loadPokemonList()
        //loadPokemon(pokeName!!)
    }


    fun loadPokemonList() {
        viewModelScope.launch {
            repository.getPokemonList()
        }
    }

    fun loadPokemon(name: String) : Pokemon{
        viewModelScope.launch {
            repository.getPokemon(name)
        }
        if (Pokemon != null) {
            return Pokemon
        } else {
            return null
        }
    }



}
