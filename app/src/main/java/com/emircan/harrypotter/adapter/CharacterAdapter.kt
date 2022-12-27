package com.emircan.harrypotter.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.emircan.harrypotter.model.Character
import androidx.recyclerview.widget.RecyclerView
import com.emircan.harrypotter.R
import com.emircan.harrypotter.databinding.ItemCharacterBinding
import com.emircan.harrypotter.util.downloadFromUrl
import com.emircan.harrypotter.util.placeholderProgressBar
import com.emircan.harrypotter.view.FeedFragmentDirections

class CharacterAdapter(val characterList : ArrayList<Character>) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    class CharacterViewHolder(val binding: ItemCharacterBinding ) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = DataBindingUtil.inflate<ItemCharacterBinding>(LayoutInflater.from(parent.context), R.layout.item_character,parent,false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        holder.binding.name.text = characterList[position].characterName
        holder.binding.house.text = characterList[position].characterHouse

        holder.itemView.setOnClickListener{
            val action = FeedFragmentDirections.actionFeedFragmentToCharacterFragment(characterList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.imageView.downloadFromUrl(characterList[position].characterImage,
            placeholderProgressBar(holder.itemView.context))

    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun updatecharacterList(newCharacterList : List<Character>){
        characterList.clear()
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }
}