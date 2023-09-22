package com.example.abschlussprojekt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.abschlussprojekt.data.models.PokeEntity
import com.example.abschlussprojekt.data.models.pokemon.Pokemon
import com.example.abschlussprojekt.databinding.ItemHomeBinding
import com.example.abschlussprojekt.databinding.ItemLibraryBinding
import com.example.abschlussprojekt.ui.HomeFragmentDirections
import com.example.abschlussprojekt.ui.LibraryFragmentDirections
import com.example.abschlussprojekt.ui.SharedViewModel
import java.util.Locale

class LibraryAdapter(
    val viewModel: SharedViewModel,
    private val dataSet: List<PokeEntity>) :
    RecyclerView.Adapter<LibraryAdapter.ItemLibraryHolder>() {

    inner class ItemLibraryHolder(val binding: ItemLibraryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemLibraryHolder {
        val binding = ItemLibraryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemLibraryHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ItemLibraryHolder, position: Int) {
        val pokemon = dataSet[position]

        holder.binding.ivPokemon.load(pokemon.spriteDefaultFront)
        holder.binding.tvPokenumber.text = pokemon.id.toString()
        holder.binding.tvPokeName.text = pokemon.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }


        holder.binding.cardview.setOnClickListener {
            val navController = holder.itemView.findNavController()
            viewModel.setCurrentPokemon(viewModel.toPokemon(pokemon))
            navController.navigate(LibraryFragmentDirections.actionLibraryFragmentToDetailFragment())
        }
    }
}