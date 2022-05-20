package com.hossein.tamasha.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hossein.tamasha.databinding.ItemHomeGenreListBinding
import com.hossein.tamasha.models.home.ResponseGenreList.ResponseGenreListItem
import javax.inject.Inject

class GenersAdapter @Inject constructor() : RecyclerView.Adapter<GenersAdapter.ViewHolder>() {


    private lateinit var binding: ItemHomeGenreListBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenersAdapter.ViewHolder {
        binding =
            ItemHomeGenreListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: GenersAdapter.ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        //avoid from douplicate item
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun setData(item: ResponseGenreListItem) {

            binding.apply {
                nameText.text=item.name

            }

        }

    }

    private val differCallback = object : DiffUtil.ItemCallback<ResponseGenreListItem>() {
        override fun areItemsTheSame(
            oldItem: ResponseGenreListItem,
            newItem: ResponseGenreListItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseGenreListItem,
            newItem: ResponseGenreListItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

}