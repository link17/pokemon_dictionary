package com.kakao.mobility.pokemondictionary.data

import com.google.gson.annotations.SerializedName

/**
 * 포켓몬 기본 정보.(한글이름을 가지고 있는것이 특징)
 */
open class PokemonData(
    open val id: Int = 0, // id
    open var korName: String = "", // 한글 이름
    @SerializedName("name")
    open val engName: String = "" // 영어 이름
)