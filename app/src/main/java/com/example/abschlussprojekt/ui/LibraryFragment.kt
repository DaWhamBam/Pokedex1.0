package com.example.abschlussprojekt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.abschlussprojekt.adapter.LibraryAdapter
import com.example.abschlussprojekt.adapter.SearchAdapter
import com.example.abschlussprojekt.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentLibraryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.favoritePokemon.observe(viewLifecycleOwner, Observer {
            var itemAdapter = LibraryAdapter(viewModel, it)
            binding.recyclerView.adapter = itemAdapter
        })

        binding.ivHomeSymbole.setOnClickListener {
            findNavController().navigate(LibraryFragmentDirections.actionLibraryFragmentToHomeFragment())
        }
    }
}