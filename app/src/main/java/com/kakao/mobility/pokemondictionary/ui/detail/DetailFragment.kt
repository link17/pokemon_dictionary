package com.kakao.mobility.pokemondictionary.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.rxbinding2.view.RxView
import com.kakao.mobility.pokemondictionary.R
import com.kakao.mobility.pokemondictionary.data.PokemonData
import com.kakao.mobility.pokemondictionary.databinding.FragmentDetailBinding
import com.kakao.mobility.pokemondictionary.ui.map.MapsFragment
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment :DialogFragment(){

    companion object {
        fun newInstance() = DetailFragment()
    }


    private val viewModel: DetailViewModel by lazy {
        ViewModelProviders.of(this).get(DetailViewModel::class.java).apply {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(inflater,container,false).apply {
        viewModel = this@DetailFragment.viewModel
        lifecycleOwner = this@DetailFragment
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPokemon(PokemonData(1,"피카츄","pikachu"))
        viewModel.action.observe(this, Observer {
           fragmentManager?.beginTransaction()?.add(R.id.container,MapsFragment.newInstance(it),MapsFragment.toString())?.commit()
        })
    }
}