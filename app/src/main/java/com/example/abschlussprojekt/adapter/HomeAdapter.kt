package com.example.abschlussprojekt.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.abschlussprojekt.data.models.Pokemon
import com.example.abschlussprojekt.databinding.ItemHomeBinding
import com.example.abschlussprojekt.ui.HomeFragmentDirections
import com.example.abschlussprojekt.ui.SharedViewModel

class HomeAdapter(private val viewModel: SharedViewModel, private val dataSet: List<Pokemon>) :
    RecyclerView.Adapter<HomeAdapter.ItemHomeViewHolder>() {

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
        Log.e("Pokemon", "Pokemon $pokemon")
        //val imgUri = pokemon.sprites.sprite.toUri().buildUpon().scheme("https").build()

        //holder.binding.ivPokemon.load(imgUri)
        holder.binding.tvPokeId.text = pokemon.id.toString()
        holder.binding.tvPokeName.text = pokemon.name

        holder.binding.cardview.setOnClickListener {
            val navController = holder.itemView.findNavController()
            viewModel.currentPokemon = pokemon
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment())
        }
    }
}