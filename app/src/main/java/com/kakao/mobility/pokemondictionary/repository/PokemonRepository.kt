package com.kakao.mobility.pokemondictionary.repository

import com.kakao.mobility.pokemondictionary.data.LocationData
import com.kakao.mobility.pokemondictionary.data.PokemonDetailData
import com.kakao.mobility.pokemondictionary.data.PokemonLocationResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

object PokemonRepository : PokemonDataSource{

    private val remote = RemoteDataSource()
    private val local = LocalDataSource()
    override fun getPokemonLocations(): Single<MutableMap<Int, ArrayList<LocationData>>> = remote.getPokemonLocations().subscribeOn(Schedulers.io())
    override fun getPokemon(id: Int): Single<PokemonDetailData?> = remote.getPokemon(id).subscribeOn(Schedulers.io())
//    override fun getPokemonLocations():Single<MutableMap<Int, ArrayList<LocationData>>> =  local.getPokemonLocations().filter { it == null }.let {
//        remote.getPokemonLocations().subscribeOn(Schedulers.io()).flatMap { local.saveLocations(it) }
//    }

//    override fun getPokemon(id: Int): Single<PokemonDetailData?> = local.getPokemon(id).onErrorResumeNext { remote.getPokemon(id).flatMap { local.putPokemon(it) } }

}