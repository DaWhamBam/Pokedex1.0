package com.example.abschlussprojekt.data.models.pokemon

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.abschlussprojekt.data.models.pokemonstats.PokemonStat
import com.example.abschlussprojekt.data.models.pokemontyps.PokemonTyps
import com.example.abschlussprojekt.data.models.Sprites
import com.squareup.moshi.Json

data class Pokemon(
    var id: Int,
    @Json(name = "name") var name: String,
    @Json(name = "height") var height: Int,
    @Json(name = "weight") var weight: Int,
    @Json(name = "sprites") var sprites: Sprites = Sprites(""),
    @Json(name = "types") var types: List<PokemonTyps> = listOf(),
    @Json(name = "stats") var stats: List<PokemonStat> = listOf(),
)
