package com.example.abschlussprojekt.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import coil.load
import com.example.abschlussprojekt.data.models.pokemon.Pokemon
import com.example.abschlussprojekt.data.models.pokemonhomelist.PokemonListItem
import com.example.abschlussprojekt.databinding.ItemHomeBinding
import com.example.abschlussprojekt.ui.SharedViewModel

class HomeAdapter(private val viewModel: SharedViewModel, private val dataSet: List<Pokemon>) :
    RecyclerView.Adapter<HomeAdapter.ItemHomeViewHolder>() {

    private var currentRecyclerView: RecyclerView? = null

    private var isLoading = false

    public fun addPokemonPage(){
        isLoading = false
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        currentRecyclerView = recyclerView
        recyclerView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val layoutManager = recyclerView.layoutManager
            layoutManager?.let {
                if(layoutManager is GridLayoutManager) {
//                    val visibleItemCount: Int = layoutManager.childCount
                    val totalItemCount: Int = layoutManager.itemCount
                    val lastVisibleItemPos = layoutManager.findLastVisibleItemPosition()

                    if (!isLoading) {
                        if (lastVisibleItemPos > totalItemCount - 20) { //(lastVisibleItemPos > totalItemCount - 20) {
                            viewModel.loadPokemonPage(totalItemCount)
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

        holder.binding.ivPokemon.load(pokemon.sprites.front_default)
        holder.binding.tvPokeId.text = pokemon.id.toString()
        holder.binding.tvPokeName.text = pokemon.name

        /*
        holder.binding.cardview.setOnClickListener {
            val navController = holder.itemView.findNavController()
            viewModel.currentPokemon = pokemon
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment())
        }

         */
    }
}