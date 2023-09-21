package com.example.abschlussprojekt.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.abschlussprojekt.data.models.PokeEntity

@Dao
interface PokemonDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: PokeEntity)

    @Update
    suspend fun update(pokemon: PokeEntity)

    @Query("SELECT * FROM PokeEntity")
    fun getAll(): LiveData<List<PokeEntity>>

    @Query("SELECT * FROM PokeEntity WHERE id =:key")
    fun getById(key: Int): LiveData<PokeEntity>

    @Query("DELETE FROM PokeEntity WHERE id =:key")
    suspend fun deletePokemon(key: Int)

}