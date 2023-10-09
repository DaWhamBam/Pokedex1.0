package com.example.abschlussprojekt.adapter

import android.graphics.Color
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
import com.google.android.material.color.MaterialColors.getColor
import com.google.android.material.textfield.TextInputLayout
import java.util.Locale

class HomeAdapter(
    private val viewModel: SharedViewModel,
    val setCharacter: (Pokemon) -> Unit,  // I have tested here how individual things can be fetched from the ViewModel.
    private val dataSet: List<Pokemon>
) :
    RecyclerView.Adapter<HomeAdapter.ItemHomeViewHolder>() {

    private var isLoading = false

    // The function helps so that only a certain amount of Pokemon are loaded and the adapter is updated.
    fun addPokemonPage() {
        isLoading = false
        notifyDataSetChanged()
    }

 // When the RecyclerView scrolls, it registers how many Pokemon can still be displayed until Pokemon need to be reloaded.
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.setOnScrollChangeListener { _, _, _, _, _ ->
            val layoutManager = recyclerView.layoutManager
            layoutManager?.let {
                if (layoutManager is GridLayoutManager) {
                    val totalItemCount: Int = layoutManager.itemCount
                    val lastVisibleItemPos = layoutManager.findLastVisibleItemPosition()

                    if (!isLoading) {
                        if (lastVisibleItemPos > totalItemCount - 20) {
                            viewModel.loadPokemonPage(totalItemCount) // here the Pokemon are actually loaded
                            isLoading = true
                        }
                    }
                }
            }
        }
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
        Log.e("pokemon", "Hier: $pokemon")

        holder.binding.ivPokemon.load(pokemon.sprites.front_default)
        holder.binding.tvPokeId.text = pokemon.id.toString()
        holder.binding.tvPokeName.text = pokemon.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }

        when (pokemon.types.first().type.name) {

            "grass" -> holder.binding.constraint.setBackgroundResource(R.color.plant)
            "ice" -> holder.binding.constraint.setBackgroundResource(R.color.ice)
            "poison" -> holder.binding.constraint.setBackgroundResource(R.color.poisen)
            "psychic" -> holder.binding.constraint.setBackgroundResource(R.color.psych)
            "rock" -> holder.binding.constraint.setBackgroundResource(R.color.stone)
            "steel" -> holder.binding.constraint.setBackgroundResource(R.color.steel)
            "dark" -> holder.binding.constraint.setBackgroundResource(R.color.unlight)
            "water" -> holder.binding.constraint.setBackgroundResource(R.color.water)
            "bug" -> holder.binding.constraint.setBackgroundResource(R.color.bug)
            "dragon" -> holder.binding.constraint.setBackgroundResource(R.color.dragon)
            "electric" -> holder.binding.constraint.setBackgroundResource(R.color.electric)
            "fairy" -> holder.binding.constraint.setBackgroundResource(R.color.fairy)
            "fighting" -> holder.binding.constraint.setBackgroundResource(R.color.fight)
            "fire" -> holder.binding.constraint.setBackgroundResource(R.color.fire)
            "flying" -> holder.binding.constraint.setBackgroundResource(R.color.flight)
            "ghost" -> holder.binding.constraint.setBackgroundResource(R.color.ghost)
            "ground" -> holder.binding.constraint.setBackgroundResource(R.color.ground)
            "normal" -> holder.binding.constraint.setBackgroundResource(R.color.normal)

            else -> holder.binding.constraint.setBackgroundResource(R.color.home_back)
        }


        holder.binding.cardview.setOnClickListener {
            val navController = holder.itemView.findNavController()
            setCharacter(pokemon)
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment())
        }
    }
}