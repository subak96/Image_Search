package com.example.image_search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.image_search.databinding.FragmentBoxBinding


class Box_Fragment : Fragment() {

    private lateinit var Scontext: Context
    private lateinit var binding: FragmentBoxBinding
    private lateinit var boxAdapter: Box_Adapter

    //좋아요를 누르면 저장하는곳
    private var likebox: List<Search_Item> = listOf()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Scontext = context
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val main = activity as MainActivity
        likebox - main.likebox

        boxAdapter = Box_Adapter(Scontext).apply {
            Item = likebox.toMutableList()
        }

        binding = FragmentBoxBinding.inflate(inflater, container, false).apply {
            likebox.layoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            likebox.adapter = boxAdapter
        }
        return binding.root
    }


}