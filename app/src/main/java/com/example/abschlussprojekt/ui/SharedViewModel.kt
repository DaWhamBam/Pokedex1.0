package com.example.abschlussprojekt.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.abschlussprojekt.data.local.getDatabase
import com.example.abschlussprojekt.data.models.PokeEntitiy
import com.example.abschlussprojekt.data.models.pokemon.Pokemon
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

//    lateinit var completePokemon: PokemonForData


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

    fun toPokemonEntity(pokemon: Pokemon): PokeEntitiy {
        return PokeEntitiy(
            id = pokemon.id,
            height = pokemon.height.toString(),
            weight = pokemon.weight.toString(),
            spriteDefaultFront = pokemon.sprites.front_default,
            type1 = pokemon.types.first().type.name,
            type2 = pokemon.types.last().type.name,
            hp = pokemon.stats[0].stat.name,
            hpInt = pokemon.stats[0].base_stat
        )
    }

    fun insertPoke(pokemon: PokeEntitiy) {
        viewModelScope.launch {
            repository.insert(pokemon)
        }
    }

    fun deletePoke(pokemon: PokeEntitiy) {
        viewModelScope.launch {
            repository.delete(pokemon.id)
        }
    }


}
