package com.rookieandroid.meta.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rookieandroid.meta.Models
import com.rookieandroid.meta.R

class GifAdapter : PagingDataAdapter<Models.Giphy, GifAdapter.GifViewHolder>(GifComparator)//RecyclerView.Adapter<GifAdapter.GifViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gif_item, parent, false)
        return GifViewHolder(view)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) { holder.bind(getItem(position)!!) }

    class GifViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        private val title : TextView = itemView.findViewById(R.id.gif_title)
        private val gifImage : ImageView = itemView.findViewById(R.id.gif)

        fun bind(gif : Models.Giphy)
        {
            title.text = gif.title
            val url = gif.images.original.url
            Glide.with(itemView).load(url).into(gifImage)
        }
    }

    object GifComparator : DiffUtil.ItemCallback<Models.Giphy>() {
        override fun areItemsTheSame(oldItem: Models.Giphy, newItem: Models.Giphy): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Models.Giphy, newItem: Models.Giphy): Boolean = oldItem == newItem
    }
}