package com.example.abschlussprojekt.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.abschlussprojekt.data.models.PokeEntitiy
import com.example.abschlussprojekt.data.models.pokemon.Pokemon

@Dao
interface PokemonDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: PokeEntitiy)

    @Update
    suspend fun update(pokemon: PokeEntitiy)

    @Query("SELECT * FROM PokeEntitiy")
    fun getAll(): LiveData<List<PokeEntitiy>>

    @Query("SELECT * FROM PokeEntitiy WHERE id =:key")
    fun getById(key: Int): LiveData<PokeEntitiy>

    @Query("DELETE FROM PokeEntitiy WHERE id =:key")
    suspend fun deletePokemon(key: Int)

}