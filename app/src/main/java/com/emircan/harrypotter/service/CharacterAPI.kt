package com.emircan.harrypotter.service
import com.emircan.harrypotter.model.Character
import io.reactivex.Single
import retrofit2.http.GET

interface CharacterAPI {

    //https://raw.githubusercontent.com/Emirken/HarryPotterJson/main/json-data.json
    //Base Url -> https://raw.githubusercontent.com/
    //Ext -> Emirken/HarryPotterJson/main/json-data.json

    @GET("Emirken/HarryPotterJson/main/json-data.json")
    fun getCharacters():Single<List<Character>>
}