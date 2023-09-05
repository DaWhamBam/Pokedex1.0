package com.example.abschlussprojekt.data.models

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<Type>,
    val stats: List<Stats>,
)
