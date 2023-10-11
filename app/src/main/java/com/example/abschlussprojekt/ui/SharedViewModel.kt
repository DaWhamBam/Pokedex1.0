package com.example.abschlussprojekt.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.abschlussprojekt.data.local.getDatabase
import com.example.abschlussprojekt.data.models.PokeEntity
import com.example.abschlussprojekt.data.models.Sprites
import com.example.abschlussprojekt.data.models.pokemon.Pokemon
import com.example.abschlussprojekt.data.models.pokemonstats.PokemonStat
import com.example.abschlussprojekt.data.models.pokemonstats.StatsName
import com.example.abschlussprojekt.data.models.pokemontyps.PokemonTyps
import com.example.abschlussprojekt.data.models.pokemontyps.Type
import com.example.abschlussprojekt.data.remote.AppRepository
import com.example.abschlussprojekt.data.remote.PokeApi
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)

    var inputText = MutableLiveData<String>()  // The search input is recorded live and observed in the home fragment.

    private val _currentPokemon : MutableLiveData<PokeEntity> = MutableLiveData()
    val currentPokemon : LiveData<PokeEntity>  // The current selected Pokemon as entity
        get() = _currentPokemon

    private val repository = AppRepository(PokeApi, database)
    var pokemonList = mutableListOf<Pokemon>()  // All Pokemon with name only
    val newPokemonPage = repository.newPokemonPage // All Pokemon currently loaded with full details
    val searchPokemon = repository.searchPokemon  // The searched Pokemon only with name
    val favoritePokemon = repository.favoritePokemon // The favorite Pokemon with all the details
    var filterPokemon = newPokemonPage.value

    private var _newfilter : MutableLiveData<List<Pokemon>> = MutableLiveData()
    val newfilter : LiveData<List<Pokemon>>
        get() = _newfilter

    init {
        loadPokemonList()
    }


    /*
    Here is the variable that will be used in the Home Adapter. It was only a test so that I have
    done it once. At the same time the Pokemon is converted to a
    PokemonEntity to be used further. For the database for example.
     */
    val setCurrentPokemon : (Pokemon) -> Unit = {
        val newEntity = toPokemonEntity(it)
        _currentPokemon.postValue(newEntity)
    }

    fun filteredPokemonList(type: String) {
        _newfilter.value = filterPokemon?.filter { it.types.first().type.name == type}!!
    }


    /*
    The function ensures that all Pokemon with names and 50 Pokemon with
    all details are loaded at startup.
     */
    fun loadPokemonList() {
        viewModelScope.launch {
            repository.getPokemonItemList()
            repository.loadPokemonPage(0)
        }
    }

    fun loadPokemonPage(offset: Int) {
        viewModelScope.launch {
            repository.loadPokemonPage(offset)
        }
    }

    fun loadSearchPokemon(searchTerm: String) {
        viewModelScope.launch {
            repository.loadSearchPokemon(searchTerm)
        }
    }

    fun toPokemonEntity(pokemon: Pokemon): PokeEntity {
        return PokeEntity(
            id = pokemon.id,
            name = pokemon.name,
            height = pokemon.height.toString(),
            weight = pokemon.weight.toString(),
            spriteDefaultFront = pokemon.sprites.front_default,
            type1 = pokemon.types.first().type.name,
            type2 = pokemon.types.last().type.name,
            hpInt = pokemon.stats[0].base_stat,
            atkInt = pokemon.stats[1].base_stat,
            defInt = pokemon.stats[2].base_stat,
            spdInt = pokemon.stats[3].base_stat,
            spDefInt = pokemon.stats[4].base_stat,
            spAtkInt = pokemon.stats[5].base_stat
        )
    }

    fun insertPoke() {
        viewModelScope.launch {
            repository.insert(currentPokemon.value!!)
        }
    }

    fun deletePoke() {
        viewModelScope.launch {
            repository.delete(currentPokemon.value!!.id)
        }
    }

    fun toPokemon(pokemon: PokeEntity): Pokemon {
        return Pokemon(
            id = pokemon.id,
            name = pokemon.name,
            height = pokemon.height.toInt(),
            weight = pokemon.weight.toInt(),
            sprites = Sprites(pokemon.spriteDefaultFront),
            types = listOf(PokemonTyps(Type(pokemon.type1)), PokemonTyps(Type(pokemon.type2))),
            stats = listOf(
                PokemonStat(pokemon.hpInt,0,StatsName("HP")),
                PokemonStat(pokemon.atkInt, 0, StatsName("ATK")),
                PokemonStat(pokemon.defInt, 0, StatsName("DEF")),
                PokemonStat(pokemon.spdInt, 0, StatsName("SPD")),
                PokemonStat(pokemon.spAtkInt, 0, StatsName("SP-ATK")),
                PokemonStat(pokemon.spDefInt, 0, StatsName("SP-DEF"))
                )
        )
    }
}
