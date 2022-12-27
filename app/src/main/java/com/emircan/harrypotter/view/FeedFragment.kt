package com.emircan.harrypotter.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.emircan.harrypotter.adapter.CharacterAdapter
import com.emircan.harrypotter.databinding.FragmentFeedBinding
import com.emircan.harrypotter.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    private lateinit var binding : FragmentFeedBinding
    private lateinit var viewModel : FeedViewModel
    private val characterAdapter = CharacterAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = characterAdapter

        binding.swipeRefresh.setOnRefreshListener {
            binding.recyclerView.visibility = View.GONE
            binding.characterError.visibility = View.GONE
            binding.characterLoading.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            binding.swipeRefresh.isRefreshing = false
        }

        observeLiveData()

    }


    private fun observeLiveData(){
        viewModel.characters.observe(viewLifecycleOwner, Observer { characters ->
            characters?.let {
                binding.recyclerView.visibility = View.VISIBLE
                characterAdapter.updatecharacterList(characters)
            }
        })

        viewModel.characterError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it){
                    binding.characterError.visibility = View.VISIBLE
                }else{
                    binding.characterError.visibility = View.GONE
                }
            }
        })

        viewModel.characterLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it){
                    binding.characterLoading.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.characterError.visibility = View.GONE
                }else{
                    binding.characterLoading.visibility = View.GONE
                }
            }
        })
    }


}