package com.kakao.mobility.pokemondictionary.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kakao.mobility.pokemondictionary.data.PokemonNameResponse
import com.kakao.mobility.pokemondictionary.databinding.ItemPokemonBinding

class SearchAdapter(val mainViewModel: MainViewModel) : ListAdapter<PokemonNameResponse,PokemonViewHolder>(SearchListDiff){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PokemonViewHolder(ItemPokemonBinding.inflate(
        LayoutInflater.from(parent.context),parent,false),mainViewModel)

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(position)
    }
}


class PokemonViewHolder(val binding:ItemPokemonBinding,val mainViewModel: MainViewModel) : RecyclerView.ViewHolder(binding.root){

    fun bind(position: Int){
        binding.viewModel = mainViewModel
        binding.position = position
    }

}

object SearchListDiff : DiffUtil.ItemCallback<PokemonNameResponse>() {

    override fun areItemsTheSame(
        oldItem: PokemonNameResponse,
        newItem: PokemonNameResponse
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: PokemonNameResponse,
        newItem: PokemonNameResponse
    ): Boolean {
        return oldItem.id == newItem.id
    }
}