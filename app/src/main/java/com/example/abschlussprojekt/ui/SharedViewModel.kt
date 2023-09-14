package com.example.abschlussprojekt.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.abschlussprojekt.data.local.getDatabase
import com.example.abschlussprojekt.data.models.pokemon.Pokemon
import com.example.abschlussprojekt.data.models.pokemontyps.PokemonForData
import com.example.abschlussprojekt.data.remote.AppRepository
import com.example.abschlussprojekt.data.remote.PokeApi
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)

    var inputText = MutableLiveData<String>()

    lateinit var currentPokemon: Pokemon

    private val repository = AppRepository(PokeApi, database)
    val pokeItemList = repository.pokeItemList
    val pokemonList = mutableListOf<Pokemon>()
    val newPokemonPage = repository.newPokemonPage

    lateinit var completePokemon: PokemonForData


    init {
        loadPokemonList()
    }


    fun loadPokemonList() {
        viewModelScope.launch {
            repository.getPokemonItemList()
            repository.loadPokemonPage(0)
        }
    }

    fun loadPokemon(name: String){
        viewModelScope.launch {
            repository.getPokemon(name)
        }
    }

    fun loadPokemonPage(offset: Int) {
        viewModelScope.launch {
            repository.loadPokemonPage(offset)
        }
    }


}
