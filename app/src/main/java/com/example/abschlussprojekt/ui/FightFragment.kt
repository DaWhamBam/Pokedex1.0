package com.example.abschlussprojekt.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.abschlussprojekt.R
import com.example.abschlussprojekt.databinding.FragmentFightBinding
import okhttp3.internal.notify

class FightFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentFightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentPokemon.observe(viewLifecycleOwner, Observer {
            Log.e("Pokemon", "${viewModel.currentPokemon.value}")
            binding.ivPokeBack.load(it.spriteDefaultBack)
            binding.tvPokeName.text = it.name.capitalize()
            binding.tvAtk1.text = it.atk1.capitalize()
            binding.tvAtk2.text = it.atk2.capitalize()
            binding.tvAtk3.text = it.atk3.capitalize()
            binding.tvAtk4.text = it.atk4.capitalize()
            binding.tvBaseHpInt.text = "100"
            binding.tvCurrentHpInt.text = it.pokeHp.toString()
        })

        viewModel.randomPokemon.observe(viewLifecycleOwner, Observer {
            Log.e("Random", "${viewModel.randomPokemon.value}")
            binding.ivPokeEnemie.load(it.spriteDefaultFront)
            binding.tvPokeNameEnemie.text = it.name.capitalize()
            binding.tvBaseHpEnemieInt.text = "100"
            binding.tvCurrentHpEnemieInt.text = it.pokeHp.toString()

        })

        viewModel.enemieHp.observe(viewLifecycleOwner, Observer {
            binding.tvCurrentHpEnemieInt.text = viewModel.enemieHp.value.toString()
        })

        binding.tvAtk1.setOnClickListener {
            Log.e("Hp", "${viewModel.randomPokemon.value!!.pokeHp}")
            if (viewModel.randomPokemon.value?.pokeHp!! > 0) {
                viewModel.randomPokemon.value!!.pokeHp -= viewModel.currentPokemon.value!!.atk1Int
                viewModel.setEnemieHp(viewModel.randomPokemon.value!!.pokeHp)
            } else {
                Log.e("HpElse", "${viewModel.currentPokemon.value!!.pokeHp}")
            }
        }

        binding.ivBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}