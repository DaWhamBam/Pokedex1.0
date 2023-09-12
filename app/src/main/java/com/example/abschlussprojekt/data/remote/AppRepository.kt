package com.example.abschlussprojekt.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.abschlussprojekt.data.local.PokemonDatabase
import com.example.abschlussprojekt.data.models.pokemon.Pokemon
import com.example.abschlussprojekt.data.models.pokemonhomelist.PokemonListItem

class AppRepository(private val api: PokeApi, private val database: PokemonDatabase) {

    private val _pokeList = MutableLiveData<List<PokemonListItem>>()
    val pokeList: LiveData<List<PokemonListItem>>
        get() = _pokeList

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon>
        get() = _pokemon

    suspend fun getPokemonList(){
        _pokeList.value = api.retrofitService.getPokemonList().results
    }

    suspend fun getPokemon(name: String) {
        _pokemon.value = api.retrofitService.getPokemon(name)
    }
}