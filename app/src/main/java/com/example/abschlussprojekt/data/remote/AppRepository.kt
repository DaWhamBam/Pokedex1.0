package com.example.abschlussprojekt.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.abschlussprojekt.data.local.PokemonDatabase
import com.example.abschlussprojekt.data.models.pokemon.Pokemon
import com.example.abschlussprojekt.data.models.pokemonhomelist.PokemonListItem

class AppRepository(private val api: PokeApi, private val database: PokemonDatabase) {

    val pokemonPageSize = 50

    private val _pokeItemList = MutableLiveData<List<PokemonListItem>>()
    val pokeItemList: LiveData<List<PokemonListItem>>
        get() = _pokeItemList

    private val _newPokemonPage = MutableLiveData<MutableList<Pokemon>>(mutableListOf())
    val newPokemonPage: LiveData<MutableList<Pokemon>>
        get() = _newPokemonPage


    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon>
        get() = _pokemon

    suspend fun getPokemonItemList(){
        val response = api.retrofitService.getPokemonItemList()
        _pokeItemList.value = response.results
    }

    suspend fun getPokemon(name: String) {
        _pokemon.value = api.retrofitService.getPokemon(name)
    }

    suspend fun loadPokemonPage(offset: Int) {
        _pokeItemList.value?.let { pokemonItemList ->
            val loadPokemonList = pokemonItemList.subList(offset, offset+50)
            val newPokemon = mutableListOf<Pokemon>()
            loadPokemonList.forEach {
                newPokemon.add(api.retrofitService.getPokemon(it.name))
            }
            // notify observers
            _newPokemonPage.value = newPokemon
        }
    }
}