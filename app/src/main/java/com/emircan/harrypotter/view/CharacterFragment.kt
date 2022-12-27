package com.emircan.harrypotter.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.navArgs
import com.emircan.harrypotter.databinding.FragmentCharacterBinding
import com.emircan.harrypotter.util.downloadFromUrl
import com.emircan.harrypotter.util.placeholderProgressBar
import com.emircan.harrypotter.viewmodel.CharacterViewModel


class CharacterFragment : Fragment() {

    val args : CharacterFragmentArgs by navArgs()
    //private var characterUuid = 0
    private lateinit var binding : FragmentCharacterBinding
    private lateinit var viewModel : CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myUuid =args.characterUuid

        viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        viewModel.getDataFromRoom(myUuid)


        observeLiveData()
    }

    private fun observeLiveData(){

        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer { character ->
            character?.let {
                binding.characterName.text = "Character Name : ${character.characterName}"
                binding.characterSpecies.text = "Character Species :  ${character.characterSpecies}"
                binding.characterGender.text = "Character Gender : ${character.characterGender}"
                binding.characterHouse.text = "Character House : ${character.characterHouse}"
                binding.characterBirth.text = "Character Birth : ${character.characterBirth}"
                binding.characterWandWood.text = " Wand Wood : ${character.characterWandWood}"
                binding.characterWandCore.text = " Wand Core : ${character.characterWandCore}"
                binding.characterWandLength.text = " Wand Length : ${character.characterWandLength.toString()}"
                binding.characterPatronus.text = "Patronus : ${character.characterPatronus}"
                binding.characterActor.text = "Actor : ${character.characterActor}"
                context?.let {
                    binding.characterImage.downloadFromUrl(character.characterImage,
                        placeholderProgressBar(it))
                }


            }
        })

    }
}