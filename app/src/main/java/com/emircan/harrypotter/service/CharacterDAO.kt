package com.emircan.harrypotter.service
import androidx.room.Dao
import com.emircan.harrypotter.model.Character
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharacterDAO {

    //Data Access Object

    @Insert
    suspend fun insertAll(vararg characters :Character) : List<Long>

    //Insert -> Insert Into
    //suspend -> Coroutine , pause & resume
    //vararg -> multiple character object
    // List<Long> -> primary Keys

    @Query("SELECT * FROM character")
    suspend fun getAllCharacters() : List<Character>

    @Query("SELECT * FROM character WHERE uuid = :characterId")
    suspend fun getCharacter(characterId : Int) : Character

    @Query("DELETE FROM character")
    suspend fun deleteAllCharacters()

}