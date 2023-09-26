package com.example.abschlussprojekt.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import coil.load
import com.example.abschlussprojekt.R
import com.example.abschlussprojekt.data.models.pokemon.Pokemon
import com.example.abschlussprojekt.data.models.pokemonhomelist.PokemonListItem
import com.example.abschlussprojekt.databinding.ItemHomeBinding
import com.example.abschlussprojekt.ui.HomeFragmentDirections
import com.example.abschlussprojekt.ui.SharedViewModel
import com.google.android.material.textfield.TextInputLayout
import java.util.Locale

class SearchAdapter(
    val setCharacter: (Pokemon) -> Unit,
    private var dataSet: List<Pokemon>) :
    RecyclerView.Adapter<SearchAdapter.ItemHomeViewHolder>() {

    fun setPokemon(searchPoke: List<Pokemon>){
        dataSet = searchPoke
        notifyDataSetChanged()
    }

    inner class ItemHomeViewHolder(val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHomeViewHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ItemHomeViewHolder, position: Int) {
        val pokemon = dataSet[position]

        holder.binding.ivPokemon.load(pokemon.sprites.front_default)
        holder.binding.tvPokeId.text = pokemon.id.toString()
        holder.binding.tvPokeName.text = pokemon.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }

        holder.binding.cardview.setOnClickListener {
            val navController = holder.itemView.findNavController()
            setCharacter(pokemon)
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment())
        }
    }
}