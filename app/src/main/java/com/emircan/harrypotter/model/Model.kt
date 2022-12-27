package com.emircan.harrypotter.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Character(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val characterName :String?,

    @ColumnInfo(name = "species")
    @SerializedName("species")
    val characterSpecies : String?,

    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    val characterGender : String?,

    @ColumnInfo(name = "house")
    @SerializedName("house")
    val characterHouse : String?,

    @ColumnInfo(name = "dateOfBirth")
    @SerializedName("dateOfBirth")
    val characterBirth : String?,

    @ColumnInfo(name = "wood")
    @SerializedName("wood")
    val characterWandWood : String?,

    @ColumnInfo(name = "core")
    @SerializedName("core")
    val characterWandCore: String?,


    @ColumnInfo(name = "length")
    @SerializedName("length")
    val characterWandLength : Int?,



    @ColumnInfo(name = "patronus")
    @SerializedName("patronus")
    val characterPatronus : String?,

    @ColumnInfo(name = "actor")
    @SerializedName("actor")
    val characterActor : String?,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    val characterImage : String?) {

    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}