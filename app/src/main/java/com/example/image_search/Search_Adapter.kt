package com.example.image_search

import android.content.ClipData.Item
import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.recyclerview.widget.RecyclerView
import com.example.image_search.databinding.SearchListBinding
import com.bumptech.glide.Glide

class Search_Adapter(private val Scontext: Context) : RecyclerView.Adapter<Search_Adapter.ViewHolder>() {

    var Item = ArrayList<Search_Item>()

    fun clear() {
        Item.clear()
        notifyDataSetChanged()
    }
    inner class ViewHolder(binding: SearchListBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        var image: ImageView = binding.searchImage
        var like: ImageView = binding.like
        var title: TextView = binding.title
        var clock: TextView = binding.clock
        var itemlist: ConstraintLayout = binding.Itemlist


        init {
            like.visibility = View.GONE
            image.setOnClickListener(this)
            itemlist.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
            val item = Item[position]

            item.like = !item.like

            if (item.like){
                (Scontext as MainActivity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = Item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val glideItem = Item[position]

        Glide.with(Scontext)
            .load(glideItem.uri)
            .into(holder.image)
    }





}

