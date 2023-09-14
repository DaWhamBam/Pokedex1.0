package com.example.abschlussprojekt.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.abschlussprojekt.data.models.pokemonstats.PokemonStat
import com.example.abschlussprojekt.data.models.pokemontyps.PokemonForData
import com.example.abschlussprojekt.data.models.pokemontyps.PokemonTyps

@Entity
data class PokeEntitiy(
    @PrimaryKey
    val id: Int = 0,
)