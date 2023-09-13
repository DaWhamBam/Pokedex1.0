package com.example.abschlussprojekt.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.abschlussprojekt.data.models.PokeEntitiy
import com.example.abschlussprojekt.data.models.pokemon.Pokemon

@Database(entities = [PokeEntitiy::class], version = 1)
abstract class PokemonDatabase: RoomDatabase() {

    abstract val pokemonDatabaseDao: PokemonDatabaseDao
}

private lateinit var INSTANCE: PokemonDatabase

fun getDatabase(context: Context): PokemonDatabase {
    synchronized(PokemonDatabase::class.java) {
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                PokemonDatabase::class.java,
                name = "pokemon_database"
            )
                .build()
        }
    }
    return INSTANCE
}