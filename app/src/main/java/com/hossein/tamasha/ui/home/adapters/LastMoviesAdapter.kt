package com.hossein.tamasha.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hossein.tamasha.databinding.ItemHomeMoviesLastBinding
import com.hossein.tamasha.databinding.ItemHomeMoviesTopBinding
import com.hossein.tamasha.models.home.ResponseMoviesList
import com.hossein.tamasha.models.home.ResponseMoviesList.*
import javax.inject.Inject

class LastMoviesAdapter @Inject constructor() :
    RecyclerView.Adapter<LastMoviesAdapter.ViewHolder>() {


    private lateinit var binding: ItemHomeMoviesLastBinding

     private var moviesList = emptyList<Data>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LastMoviesAdapter.ViewHolder {
        binding =
            ItemHomeMoviesLastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: LastMoviesAdapter.ViewHolder, position: Int) {
        holder.bindItems(moviesList[position])
        //avoid from douplicate item
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = moviesList.size
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: Data) {

            binding.apply {
                movieName.text = item.title
                movieRate.text = item.imdbRating
                movieCountry.text = item.country
                movieYear.text = item.year
                moviesPosterImg.load(item.poster) {
                    crossfade(true)
                    crossfade(800)
                }
            }

        }

    }

    fun setData(data:List<Data>){
        val moviesDiffUtil=MoviesDiffUtils(moviesList,data)
        val diffUtils=DiffUtil.calculateDiff(moviesDiffUtil)
        moviesList=data
        diffUtils.dispatchUpdatesTo(this)
    }

    class MoviesDiffUtils(private val oldItem: List<Data>, private val newItem: List<Data>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }


    }

}