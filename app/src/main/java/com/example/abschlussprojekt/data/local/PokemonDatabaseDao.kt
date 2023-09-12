package com.example.abschlussprojekt.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.abschlussprojekt.data.models.pokemon.Pokemon

@Dao
interface PokemonDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: Pokemon)

    @Update
    suspend fun update(pokemon: Pokemon)

    @Query("SELECT * FROM Pokemon")
    fun getAll(): LiveData<List<Pokemon>>

    @Query("SELECT * FROM Pokemon WHERE id =:key")
    fun getById(key: Int): LiveData<Pokemon>

    @Query("DELETE FROM Pokemon WHERE id =:key")
    suspend fun deletePokemon(key: Int)

}