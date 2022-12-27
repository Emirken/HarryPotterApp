package com.emircan.harrypotter.viewmodel
import android.app.Application
import android.widget.Toast
import com.emircan.harrypotter.model.Character
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emircan.harrypotter.service.CharacterAPIService
import com.emircan.harrypotter.service.CharacterDatabase
import com.emircan.harrypotter.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {

    private val characterApiService = CharacterAPIService()
    private val disposable = CompositeDisposable()
    private var customPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    val characters = MutableLiveData<List<Character>>()
    val characterError = MutableLiveData<Boolean>()
    val characterLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val updateTime = customPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime){
            getDataFromSQLite()
        }else{
            getDataFromAPI()
        }


    }

    fun refreshFromAPI(){
        getDataFromAPI()
    }

    private fun getDataFromSQLite(){
        characterLoading.value = true
        launch {
            val characters = CharacterDatabase(getApplication()).characterDao().getAllCharacters()
            showCharacters(characters)
            Toast.makeText(getApplication(),"Characters From SQLite",Toast.LENGTH_LONG).show()
        }
    }

    private fun getDataFromAPI(){

        characterLoading.value = true
        disposable.add(
            characterApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Character>>(){
                    override fun onSuccess(t: List<Character>) {
                        storeInSQLite(t)
                        Toast.makeText(getApplication(),"Characters From API",Toast.LENGTH_LONG).show()

                    }

                    override fun onError(e: Throwable) {
                        characterLoading.value = false
                        characterError.value = true
                        e.printStackTrace()
                    }

                })
        )

    }

    private fun showCharacters(characterList : List<Character>){

        characters.value = characterList
        characterError.value = false
        characterLoading.value = false

    }

    private fun storeInSQLite(list: List<Character>){
        launch { 
            val dao = CharacterDatabase(getApplication()).characterDao()
            dao.deleteAllCharacters()
            val listLong = dao.insertAll(*list.toTypedArray()) // -> list -> individual
            var i = 0
            while (i < list.size){
                list[i].uuid = listLong[i].toInt()
                i = i + 1
            }

            showCharacters(list)
        }

        customPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}