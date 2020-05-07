package com.kakao.mobility.pokemondictionary.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kakao.mobility.pokemondictionary.data.LocationData
import com.kakao.mobility.pokemondictionary.data.PokemonData
import com.kakao.mobility.pokemondictionary.data.PokemonDetailData
import com.kakao.mobility.pokemondictionary.repository.PokemonRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.zipWith
import timber.log.Timber

class DetailViewModel : ViewModel() {

    val pokemonDetailData: MutableLiveData<PokemonDetailData> = MutableLiveData()

    private val _action: MutableLiveData<ArrayList<LocationData>> = MutableLiveData()
    val action: LiveData<ArrayList<LocationData>> get() = _action

    fun onClickMap() {
        _action.value = pokemonDetailData.value?.locationData
    }

    fun getPokemon(pokemonData: PokemonData) {

//        PokemonRepository.getPokemon(pokemonData.id).subscribe { t1, t2 ->
//
//            Timber.log(Log.ERROR,"$t2")
//        }

//        PokemonRepository.getPokemonLocations().subscribe { t1, t2 ->
//
//            Timber.log(Log.ERROR,"$t2")
//        }

        PokemonRepository.getPokemon(pokemonData.id)
            .zipWith(PokemonRepository.getPokemonLocations()).observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (onError = {
                Timber.log(Log.ERROR,"$it")
            }, onSuccess = {
                pair->
                pair.first?.let {
                    it.locationData = pair.second[it.id]
                    pokemonDetailData.value = it
                }
                Timber.log(Log.ERROR,"$")
            })

    }

//        PokemonRepository.getPokemon(pokemonData.id).zipWith(PokemonRepository.getPokemonLocations(),
//            BiFunction { t1, t2 ->  })

//        PokemonRepository.getPokemonLocations().flatMap { }

//        Single.( PokemonRepository.getPokemon(pokemonData.id), PokemonRepository.getPokemonLocations().observeOn(AndroidSchedulers.mainThread()),
//            BiFunction { t1, t2 ->
//
//            })

//        Single.concatArray( PokemonRepository.getPokemon(pokemonData.id), PokemonRepository.getPokemonLocations().observeOn(AndroidSchedulers.mainThread())).map {
//            Timber.log(Log.ERROR,"$it")
//        }.subscribeBy(onError = {
//            Timber.log(Log.ERROR,"$it")
//
//        },onComplete = {
//            Timber.log(Log.ERROR,"")
//
//        })



//        pokemonDetailData.value?.apply {
//            korName = pokemonData.korName
//        }


//
//    fun getLocations(id:Int) : Single<List<LocationData>>{
//
//        PokemonRepository.getPokemonLocations().observeOn(AndroidSchedulers.mainThread()).subscribeBy (onSuccess = {
//            Timber.log(Log.ERROR,"subscribeBy ${it.isEmpty()}")
//            locationMap = it
//        },onError = {
//            Timber.log(Log.ERROR,"onError ${it.toString()}")
//        })
//        return Single.just(locationMap?.get(id))
//    }
}