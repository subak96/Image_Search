package com.example.image_search

import android.content.ClipData.Item
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.image_search.databinding.SearchListBinding

class Box_Adapter( var Scontext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var Item = mutableListOf<Search_Item>()

    override fun  onCreateViewHolder(parent: ViewGroup, viewType:Int): ViewHolder {
        val binding = SearchListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = Item.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        Glide.with(Scontext)
            .load(Item[position].url)
            .into((holder as ViewHolder).image)

        holder.title.text = Item[position].title
        holder.like.visibility = View.GONE

    }

     inner class ViewHolder(binding: SearchListBinding) : RecyclerView.ViewHolder(binding.root)
    {
        var image: ImageView = binding.searchImage
        var like: ImageView = binding.like
        var title: TextView = binding.title
        var itemlist: ConstraintLayout = binding.Itemlist

        init {
            like.visibility = View.GONE
            itemlist.setOnClickListener {
                val position = adapterPosition
                (Scontext as MainActivity).Noliked(Item[position])
                if (position != RecyclerView.NO_POSITION){
                    Item.removeAt(position)
                    notifyDataSetChanged()

                }
            }

        }
    }
}