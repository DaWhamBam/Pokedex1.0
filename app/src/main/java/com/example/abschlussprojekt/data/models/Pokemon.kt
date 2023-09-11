package com.example.abschlussprojekt.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Pokemon(
    @PrimaryKey val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "height") val height: Int,
    @Json(name = "weight") val weight: Int,
    @Json(name = "sprites") val sprites: Sprites,
    //@Json(name = "types") val types: List<Type>,
   // @Json(name = "stats") val stats: List<Stats>,
)
