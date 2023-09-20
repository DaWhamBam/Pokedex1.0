package com.example.abschlussprojekt.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokeEntitiy(
    @PrimaryKey
    var id: Int = 0,
    var favorit: Boolean = false,
    var height: String,
    var weight: String,
    var spriteDefaultFront: String,
    var type1: String,
    var type2: String,
    var hp: String,
    var hpInt: String,

)