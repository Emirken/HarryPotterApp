package com.emircan.harrypotter.service

import io.reactivex.Single
import retrofit2.Retrofit
import com.emircan.harrypotter.model.Character
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class CharacterAPIService {

    //Base Url -> https://raw.githubusercontent.com/
    //Ext -> Emirken/HarryPotterJson/main/json-data.json

    private val BASE_URL ="https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CharacterAPI::class.java)

    fun getData():Single<List<Character>>{
        return api.getCharacters()
    }
}