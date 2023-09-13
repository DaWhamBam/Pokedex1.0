package com.example.abschlussprojekt.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokeEntitiy(
    @PrimaryKey
    val id: Int = 0
)