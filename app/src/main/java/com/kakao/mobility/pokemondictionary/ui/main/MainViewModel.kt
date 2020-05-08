package com.kakao.mobility.pokemondictionary.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kakao.mobility.pokemondictionary.data.PokemonDetailData
import com.kakao.mobility.pokemondictionary.data.PokemonNameResponse
import com.kakao.mobility.pokemondictionary.repository.PokemonRepository
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class MainViewModel : ViewModel() {

    val map = mutableMapOf<Int,MutableList<PokemonNameResponse>>()
    val searchList: MutableLiveData<List<PokemonNameResponse>> = MutableLiveData()

    fun getNames(){

        PokemonRepository.getPokemonNames().subscribeBy(onError = {},onSuccess = { it ->

            Timber.log(Log.ERROR,"onSuccess ")

            map.takeIf { it.isEmpty() }.apply {
                it.pokemons.forEach {
                        pokemonName->
                    pokemonName.names.forEach {
                            name->
                        name.toUpperCase().toCharArray().apply {
                            for(index in 1..this.size) {
                                for (x in 0..this.size - index) {
                                    val hashKey=name.toUpperCase().substring(x, x + index).hashCode() // hash 값을 만듬
                                    takeIf {  map.containsKey(hashKey)}.run {
                                        map[hashKey]?.add(pokemonName)
                                    }?: kotlin.run {
                                        map.put(hashKey, mutableListOf(pokemonName))
                                    }

                                }
                            }
                        }

                    }
                }
            }



            Timber.log(Log.ERROR,"onSuccess ")

//            val map=it.pokemons.map { it.id to it }



        })

    }

    fun searchPokemon(hashCode:Int){
        searchList.value = map[hashCode]?: listOf()
        Timber.log(Log.ERROR,map[hashCode].toString())
    }

    fun getPokemonName(position: Int) = searchList.value?.get(position)?.names?.get(0)

//    val user : MutableLiveData<User> = MutableLiveData()
//
//    fun getUser(){
//        TestRepository.getUser().observeOn(AndroidSchedulers.mainThread()).subscribeBy (onSuccess = {
//            Timber.log(Log.ERROR,"subscribeBy ${it.name}")
//            user.value = it
//        },onError = {
//            Timber.log(Log.ERROR,"onError ${it.toString()}")
//        })
//
//    }
}
