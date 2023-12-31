package com.example.abschlussprojekt.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.abschlussprojekt.R
import com.example.abschlussprojekt.databinding.FragmentFightBinding

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
            if (viewModel.enemieHp.value!! <= 1) {
                binding.tvBodyText.text = "Du hast gewonnen"
            }
        })

        viewModel.pokeHp.observe(viewLifecycleOwner, Observer {
            binding.tvCurrentHpInt.text = viewModel.pokeHp.value.toString()
            if (viewModel.pokeHp.value!! <= 1) {
                binding.tvBodyText.text = "Du hast gewonnen"
            }
        })

        viewModel.atkName.observe(viewLifecycleOwner, Observer {
            viewModel.pokemonFight()
        })


        binding
            .atkConstraint
            .children
            .forEach { view ->
                val viewTag = view.tag.toString()
                if (view is TextView && viewTag.startsWith("atk_")) {
                    view.setOnClickListener {
                        viewModel.setAtk(viewTag.substring(4))
                        if (viewTag.substring(4) == "atk1") {
                            binding.tvBodyText.text =
                                getString(
                                    R.string.angriffstext,
                                    viewModel.currentPokemon.value?.name?.capitalize(),
                                    viewModel.currentPokemon.value?.atk1?.capitalize()
                                )
                        } else if (viewTag.substring(4) == "atk2") {
                            binding.tvBodyText.text =
                                getString(
                                    R.string.angriffstext,
                                    viewModel.currentPokemon.value?.name?.capitalize(),
                                    viewModel.currentPokemon.value?.atk2?.capitalize()
                                )
                        } else if (viewTag.substring(4) == "atk3") {
                            binding.tvBodyText.text =
                                getString(
                                    R.string.angriffstext,
                                    viewModel.currentPokemon.value?.name?.capitalize(),
                                    viewModel.currentPokemon.value?.atk3?.capitalize()
                                )
                        } else if (viewTag.substring(4) == "atk4") {
                            binding.tvBodyText.text =
                                getString(
                                    R.string.angriffstext,
                                    viewModel.currentPokemon.value?.name?.capitalize(),
                                    viewModel.currentPokemon.value?.atk4?.capitalize()
                                )
                        }
                    }
                }
            }

        binding.ivBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}