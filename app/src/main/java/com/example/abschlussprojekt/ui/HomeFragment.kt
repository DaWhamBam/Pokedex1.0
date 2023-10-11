package com.example.abschlussprojekt.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.abschlussprojekt.adapter.FilterAdapter
import com.example.abschlussprojekt.adapter.HomeAdapter
import com.example.abschlussprojekt.adapter.SearchAdapter
import com.example.abschlussprojekt.databinding.FragmentHomeBinding
import com.google.android.material.imageview.ShapeableImageView

class HomeFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter
    private lateinit var adapterSearch: SearchAdapter
    private lateinit var filterAdapter: FilterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        /*
        The Homescreen RecyclerView is felt by two different adapters. On the one hand, the default
        filling when navigating on the homescreen and on the other hand when searching for a Pokemon.
         */
        adapter = HomeAdapter(viewModel, viewModel.pokemonList)
        adapterSearch = SearchAdapter(viewModel, listOf())
        filterAdapter = FilterAdapter(viewModel, listOf())
        binding.recyclerViewHome.adapter = adapter
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        There are two different lists. The standard list and the search list. This was done so that
        when the search is ended the standard list does not have to be refilled but the already
        loaded Pokemon are displayed again.
         */
        viewModel.newPokemonPage.observe(viewLifecycleOwner, Observer {
            viewModel.pokemonList.addAll(it)
            adapter.addPokemonPage()
            viewModel.filteredPokemonList()
        })

        viewModel.searchPokemon.observe(viewLifecycleOwner, Observer {
            adapterSearch.setPokemon(it)
        })

        viewModel.typeName.observe(viewLifecycleOwner, Observer {
            viewModel.filteredPokemonList()
        })

        viewModel.newfilter.observe(viewLifecycleOwner, Observer {
            filterAdapter.filterPokemon(it)

        })




        /*
        By activating and deactivating the search, you can decide with which adapters the
        RecyclerView should be filled.
         */
        binding.ivSearchSymbole.setOnClickListener {
            if (binding.textInput.visibility == VISIBLE) {
                binding.textInput.visibility = GONE
                binding.recyclerViewHome.adapter = adapter
                adapter.autoscrollEnabled = true
            } else {
                binding.textInput.visibility = VISIBLE
                adapterSearch.setPokemon(listOf())
                binding.textInputText.setText("")
                binding.recyclerViewHome.adapter = adapterSearch
                adapter.autoscrollEnabled = false
            }
        }

        /*
        Under Construction
        Here you can filter the individual Pokemon
         */
        binding.ivFilterSymbole.setOnClickListener {
            if (binding.cardviewFilter.visibility == VISIBLE) {
                binding.cardviewFilter.visibility = GONE
                binding.recyclerViewHome.adapter = adapter
                adapter.autoscrollEnabled = true
            } else {
                binding.cardviewFilter.visibility = VISIBLE
                binding.recyclerViewHome.adapter = filterAdapter
                adapter.autoscrollEnabled = false
            }
        }

        binding
            .typeConstraint
            .children
            .forEach { view ->
                val viewTag = view.tag.toString()
                if (view is ShapeableImageView && viewTag.startsWith("filter_")) {
                    view.setOnClickListener {
                        viewModel.setTypeName(viewTag.substring(7))
                    }
                }
            }

        binding.ivLibrarySymbole.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLibraryFragment())
        }

        viewModel.inputText.observe(viewLifecycleOwner, Observer {
            viewModel.loadSearchPokemon(it)
            adapter.addPokemonPage()
        })
    }
}