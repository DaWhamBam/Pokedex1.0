package com.example.abschlussprojekt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.abschlussprojekt.adapter.HomeAdapter
import com.example.abschlussprojekt.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapter = HomeAdapter(viewModel, viewModel.pokemonList)
        binding.recyclerViewHome.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.newPokemonPage.observe(viewLifecycleOwner, Observer {
            viewModel.pokemonList.addAll(it)
            adapter.addPokemonPage()
        }
        )

        binding.ivSearchSymbole.setOnClickListener {
            if (binding.textInput.visibility == VISIBLE) {
                binding.textInput.visibility = GONE
            } else {
                binding.textInput.visibility = VISIBLE
            }
        }

        binding.ivFilterSymbole.setOnClickListener {
            if (binding.cardviewFilter.visibility == VISIBLE) {
                binding.cardviewFilter.visibility = GONE
            } else {
                binding.cardviewFilter.visibility = VISIBLE
            }
        }

        binding.ivLibrarySymbole.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLibraryFragment())
        }
    }
}