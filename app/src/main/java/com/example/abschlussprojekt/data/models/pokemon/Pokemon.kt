package com.example.abschlussprojekt.data.models.pokemon

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.abschlussprojekt.data.models.pokemonstats.PokemonStat
import com.example.abschlussprojekt.data.models.pokemontyps.PokemonTyps
import com.example.abschlussprojekt.data.models.Sprites
import com.squareup.moshi.Json

@Entity
data class Pokemon(
    @PrimaryKey val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "height") val height: Int,
    @Json(name = "weight") val weight: Int,
    @Json(name = "sprites") val sprites: Sprites,
    @Json(name = "types") val types: List<PokemonTyps>,
    @Json(name = "stats") val stats: List<PokemonStat>,
)
