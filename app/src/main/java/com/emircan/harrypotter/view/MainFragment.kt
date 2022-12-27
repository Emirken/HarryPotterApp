package com.emircan.harrypotter.view

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.emircan.harrypotter.databinding.FragmentMainBinding
import java.io.IOException


class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    private lateinit var mediaPlayer :MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToFeedFragment()
            Navigation.findNavController(it).navigate(action)
            mediaPlayer.stop()
        }

        val audioUrl = "https://zigtone.com/download/?id=158&post=157"
        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

        try {
            mediaPlayer.setDataSource(audioUrl)
            mediaPlayer.prepare()
            mediaPlayer.start()
        }catch (e:IOException){
            e.printStackTrace()
        }




    }



}