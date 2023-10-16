package com.example.abschlussprojekt.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.abschlussprojekt.data.local.getDatabase
import com.example.abschlussprojekt.data.models.PokeEntity
import com.example.abschlussprojekt.data.models.pokemon.Pokemon
import com.example.abschlussprojekt.data.remote.AppRepository
import com.example.abschlussprojekt.data.remote.PokeApi
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)

    var inputText = MutableLiveData<String>()

    private val _currentPokemon: MutableLiveData<PokeEntity> = MutableLiveData()
    val currentPokemon: LiveData<PokeEntity>
        get() = _currentPokemon

    private val _randomPokemon: MutableLiveData<PokeEntity> = MutableLiveData()
    val randomPokemon: LiveData<PokeEntity>
        get() = _randomPokemon

    private val _enemieHp: MutableLiveData<Int> = MutableLiveData()
    val enemieHp: LiveData<Int>
        get() = _enemieHp

    private val _pokeHp = MutableLiveData<Int>()
    val pokeHp: LiveData<Int>
        get() = _pokeHp

    private val _atkName = MutableLiveData<String>()
    val atkName: LiveData<String>
        get() = _atkName

    private val _typeName = MutableLiveData<String>()
    val typeName: LiveData<String>
        get() = _typeName

    private val repository = AppRepository(PokeApi, database)
    var pokemonList = mutableListOf<Pokemon>()
    val newPokemonPage = repository.newPokemonPage
    val searchPokemon = repository.searchPokemon
    val favoritePokemon = repository.favoritePokemon

    private var _newfilter: MutableLiveData<List<Pokemon>> = MutableLiveData()
    val newfilter: LiveData<List<Pokemon>>
        get() = _newfilter

    init {
        loadPokemonList()
    }

    fun setEnemieHp(enemieHp: Int) {
        _enemieHp.value = enemieHp
    }


    fun setPokeHp(pokeHp: Int) {
        _pokeHp.value = pokeHp
    }


    fun setTypeName(typeName: String) {
        _typeName.value = typeName
    }


    fun setAtk(atkName: String) {
        _atkName.value = atkName
    }


    fun setRandomPokemon(pokemon: PokeEntity) {
        _randomPokemon.postValue(pokemon)
    }


    fun setCurrentPokemon(pokemon: PokeEntity) {
        _currentPokemon.postValue(pokemon)
    }


    fun filteredPokemonList() {
        _newfilter.value = pokemonList.filter { it.types.first().type.name == _typeName.value }
    }

    fun pokeRound() {
        if(randomPokemon.value?.pokeHp!! > 0) {
            randomPokemon.value!!.pokeHp -= currentPokemon.value!!.atk1Int
            setEnemieHp(randomPokemon.value!!.pokeHp)

        }
    }

    fun enemieRound() {
        if(currentPokemon.value?.pokeHp!! > 0) {
            Thread.sleep(3000)
            currentPokemon.value!!.pokeHp -= currentPokemon.value!!.atk1Int
            setPokeHp(currentPokemon.value!!.pokeHp)
        }
    }


    fun pokemonFight() {
        pokeRound()
        enemieRound()
    }


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


    fun toPokemonEntity(pokemon: Pokemon): PokeEntity = PokeEntity(
        id = pokemon.id,
        name = pokemon.name,
        height = pokemon.height.toString(),
        weight = pokemon.weight.toString(),
        spriteDefaultFront = pokemon.sprites.front_default,
        spriteDefaultBack = pokemon.sprites.back_default,
        type1 = pokemon.types.first().type.name,
        type2 = pokemon.types.last().type.name,
        hpInt = pokemon.stats[0].base_stat,
        atkInt = pokemon.stats[1].base_stat,
        defInt = pokemon.stats[2].base_stat,
        spdInt = pokemon.stats[3].base_stat,
        spDefInt = pokemon.stats[4].base_stat,
        spAtkInt = pokemon.stats[5].base_stat,
        atk1 = pokemon.moves[0].move.name,
        atk2 = pokemon.moves[1].move.name,
        atk3 = pokemon.moves[2].move.name,
        atk4 = pokemon.moves[3].move.name,
        atk1Int = pokemon.atk1Int,
        pokeHp = pokemon.pokeHp,
    )


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
}
