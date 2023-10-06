package com.example.abschlussprojekt.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.abschlussprojekt.data.local.PokemonDatabase
import com.example.abschlussprojekt.data.models.PokeEntity
import com.example.abschlussprojekt.data.models.pokemon.Pokemon
import com.example.abschlussprojekt.data.models.pokemonhomelist.PokemonListItem

class AppRepository(private val api: PokeApi, private val database: PokemonDatabase) {

    private val _pokeItemList = MutableLiveData<List<PokemonListItem>>() // All Pokemon in one list but only the names of them

    private val _newPokemonPage = MutableLiveData<MutableList<Pokemon>>(mutableListOf()) //All loaded Pokemon at the current state are here with all the information.  Will be expanded when scrolling the home page.
    val newPokemonPage: LiveData<MutableList<Pokemon>>
        get() = _newPokemonPage

    private val _searchPokemon = MutableLiveData<MutableList<Pokemon>>(mutableListOf()) //here are stored the Pokemon that are loaded during the search. It is emptied when the focus goes away from the search.
    val searchPokemon: LiveData<MutableList<Pokemon>>
        get() = _searchPokemon
/*
    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon>
        get() = _pokemon

 */

    val favoritePokemon = database.pokemonDatabaseDao.getAll() //the stored Pokemon from the database are here


    /*
    the Api call that loads all Pokemon but only the names without details
     */
    suspend fun getPokemonItemList() {
        val response = api.retrofitService.getPokemonItemList()
        _pokeItemList.value = response.results
    }


    /* So that hundreds of Pokemon are not loaded at the same time and the loading times of the app are too long,
    only a certain amount of Pokemon are loaded here.
    This is also important for the reason that most of the information is fetched with another Api Call.
    Thus, the names of the Pokemon are inserted into the other ApiCall and loaded.
    As soon as there are only a certain amount of Pokemon left in the Adapter/RecyclerView this function starts again.
    Here you can load the Pokemon that should be displayed on the home screen when the app is started.
     */
    suspend fun loadPokemonPage(offset: Int) {
        _pokeItemList.value?.let { pokemonItemList ->
            val loadPokemonList = pokemonItemList.subList(offset, offset + 50)
            val newPokemon = mutableListOf<Pokemon>()
            loadPokemonList.forEach {
                newPokemon.add(api.retrofitService.getPokemon(it.name))
            }
            // notify observers
            _newPokemonPage.value = newPokemon
        }
    }


    // Only Pokemon that should be loaded based on the search criteria are loaded here.
    suspend fun loadPokemonPage2(searchTerm: String) {
        _pokeItemList.value?.let { pokemonItemList ->
            val filteredPokemonList =
                pokemonItemList.filter { it.name.contains(searchTerm, ignoreCase = true) }
            val newPokemon = mutableListOf<Pokemon>()
            filteredPokemonList.forEach {
                newPokemon.add(api.retrofitService.getPokemon(it.name))
            }
            // notify observers
            _searchPokemon.value = newPokemon
        }
    }


    /*
    the functions to save or remove Pokemon to the database.
     */

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