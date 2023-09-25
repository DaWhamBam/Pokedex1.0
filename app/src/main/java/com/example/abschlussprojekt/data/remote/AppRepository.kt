package com.example.abschlussprojekt.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.abschlussprojekt.data.local.PokemonDatabase
import com.example.abschlussprojekt.data.models.PokeEntity
import com.example.abschlussprojekt.data.models.pokemon.Pokemon
import com.example.abschlussprojekt.data.models.pokemonhomelist.PokemonListItem

class AppRepository(private val api: PokeApi, private val database: PokemonDatabase) {

    private val _pokeItemList = MutableLiveData<List<PokemonListItem>>()
    val pokeItemList: LiveData<List<PokemonListItem>>
        get() = _pokeItemList

    private val _newPokemonPage = MutableLiveData<MutableList<Pokemon>>(mutableListOf())
    val newPokemonPage: LiveData<MutableList<Pokemon>>
        get() = _newPokemonPage

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon>
        get() = _pokemon

    val favoritePokemon = database.pokemonDatabaseDao.getAll()

    suspend fun getPokemonItemList(){
        val response = api.retrofitService.getPokemonItemList()
        _pokeItemList.value = response.results
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

    suspend fun loadPokemonPage2(searchTerm: String) {
        _pokeItemList.value?.let { pokemonItemList ->
            val filteredPokemonList = pokemonItemList.filter { it.name.contains(searchTerm, ignoreCase = true) }
            val newPokemon = mutableListOf<Pokemon>()
            filteredPokemonList.forEach {
                newPokemon.add(api.retrofitService.getPokemon(it.name))
            }
            // notify observers
            _newPokemonPage.value = newPokemon
        }
    }

    suspend fun insert(pokemon: PokeEntity) {
        try {
            database.pokemonDatabaseDao.insert(pokemon)
        } catch (e: Exception) {
            Log.e("InsertFun", "Insert database error: $e")
        }
    }

    suspend fun delete(id: Int) {
        try {
            database.pokemonDatabaseDao.deletePokemon(id)
        } catch (e: Exception) {
            Log.e("DeleteFun", "Delete database error: $e")
        }
    }
}