package com.example.abschlussprojekt.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokeEntity(
    @PrimaryKey
    var id: Int = 0,
    var name: String,
    var height: String,
    var weight: String,
    var spriteDefaultFront: String?,
    var spriteDefaultBack: String?,
    var type1: String,
    var type2: String,
    var hpInt: String,
    var atkInt: String,
    var defInt: String,
    var spdInt: String,
    var spDefInt: String,
    var spAtkInt: String,
    var atk1: String,
    var atk2: String,
    var atk3: String,
    var atk4: String,
    var atk1Int: Int,
    var pokeHp: Int,
)