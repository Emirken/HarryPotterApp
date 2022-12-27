package com.emircan.harrypotter.viewmodel
import android.app.Application
import com.emircan.harrypotter.model.Character
import androidx.lifecycle.MutableLiveData
import com.emircan.harrypotter.service.CharacterDatabase
import kotlinx.coroutines.launch

class CharacterViewModel(application: Application) : BaseViewModel(application) {

    val characterLiveData = MutableLiveData<Character>()

    fun getDataFromRoom(uuid : Int){
        launch {

            val dao = CharacterDatabase(getApplication()).characterDao()
            val character =  dao.getCharacter(uuid)
            characterLiveData.value = character
        }

    }
}