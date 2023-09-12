package com.example.abschlussprojekt.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.abschlussprojekt.R
import com.example.abschlussprojekt.adapter.HomeAdapter
import com.example.abschlussprojekt.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.pokeList.observe(viewLifecycleOwner, Observer{
            val pokeList = viewModel.pokeList.value
            Log.e("Pokemon", "Pokemon: $pokeList")
        binding.recyclerViewHome.adapter = HomeAdapter(viewModel, it)
    }
        )
    }
}