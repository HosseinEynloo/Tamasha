package com.hossein.tamasha.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hossein.tamasha.databinding.ItemHomeMoviesTopBinding
import com.hossein.tamasha.models.home.ResponseMoviesList
import javax.inject.Inject

class TopMoviesAdapter @Inject constructor() : RecyclerView.Adapter<TopMoviesAdapter.ViewHolder>() {


    private lateinit var binding: ItemHomeMoviesTopBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesAdapter.ViewHolder {
        binding =
            ItemHomeMoviesTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: TopMoviesAdapter.ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        //avoid from douplicate item
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = if (differ.currentList.size>5) 5 else differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun setData(item: ResponseMoviesList.Data) {

            binding.apply {
                movieName.text = item.title
                movieInfoText.text = "${item.imdbRating} | ${item.country} | ${item.year}"
                moviePosterImage.load(item.poster) {
                    crossfade(true)
                    crossfade(800)
                }
            }

        }

    }

    private val differCallback = object : DiffUtil.ItemCallback<ResponseMoviesList.Data>() {
        override fun areItemsTheSame(
            oldItem: ResponseMoviesList.Data,
            newItem: ResponseMoviesList.Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseMoviesList.Data,
            newItem: ResponseMoviesList.Data
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

}