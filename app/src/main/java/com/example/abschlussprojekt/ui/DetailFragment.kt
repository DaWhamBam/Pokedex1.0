package com.example.abschlussprojekt.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.abschlussprojekt.R
import com.example.abschlussprojekt.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentPokemon = viewModel.currentPokemon
       val imgUri = currentPokemon.sprites.front_default.toUri().buildUpon().scheme("https").build()
        val firstType = currentPokemon.types.first().type.name
        val secondType = currentPokemon.types.last().type.name
        Log.e("Pokemen", "$currentPokemon")

        val completePokemon = viewModel.completePokemon.id

        when (firstType) {
            "grass" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.plantback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.plant))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.plant))
            }
            "ice" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.iceback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.ice))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.ice))
            }
            "poison" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.poisenback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.poisen))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.poisen))
            }
            "psychic" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.psychback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.psych))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.psych))
            }
            "rock" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.stoneback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.stone))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.stone))
            }
            "steel" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.steelback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.steel))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.steel))
            }
            "dark" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.unlightback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.unlight))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.unlight))
            }
            "water" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.waterback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.water))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.water))
            }
            "bug" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.bugback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.bug))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.bug))
            }
            "dragon" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.dragonback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.dragon))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.dragon))
            }
            "electric" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.electricback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.electric))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.electric))
            }
            "fairy" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.fairyback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.fairy))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.fairy))
            }
            "fighting" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.fightback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.fight))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.fight))
            }
            "fire" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.fireback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.fire))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.fire))
            }
            "flying" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.flightback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.flight))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.flight))
            }
            "ghost" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.ghostback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.ghost))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.ghost))
            }
            "ground" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.groundback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.ground))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.ground))
            }
            "normal" -> {
                binding.frameLayout.setBackgroundResource(R.drawable.normalback)
                binding.btnType1.setBackgroundColor(getResources().getColor(R.color.normal))
                binding.btnType1.text = firstType.uppercase()
                binding.btnStartFight.setBackgroundColor(getResources().getColor(R.color.normal))
            }
            else -> { binding.frameLayout.setBackgroundResource(R.drawable.homeback)
            }
        }

        binding.ivPokemon.load(imgUri)
        binding.tvPokeName.text = currentPokemon.name.capitalize()
        binding.tvAtk.text = currentPokemon.stats.first().base_stat
    }

}