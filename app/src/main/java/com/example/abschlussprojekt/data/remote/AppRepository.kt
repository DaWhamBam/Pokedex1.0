package com.example.abschlussprojekt.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.abschlussprojekt.data.local.PokemonDatabase
import com.example.abschlussprojekt.data.models.Pokemon

class AppRepository(private val api: PokeApi, private val database: PokemonDatabase) {

    private val _pokeList = MutableLiveData<List<Pokemon>>()
    val pokeList: LiveData<List<Pokemon>>
        get() = _pokeList

    fun getPokemon() {

    }
}