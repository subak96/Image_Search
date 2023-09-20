package com.example.image_search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.image_search.databinding.FragmentSearchBinding




class Search_Fragment : Fragment() {

    private lateinit var Scontext:Context
    private lateinit var binding : FragmentSearchBinding
    private lateinit var searchAdapter: Search_Adapter
    private lateinit var gridmanager : StaggeredGridLayoutManager
    private  var Items: ArrayList<Search_Item> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
   binding = FragmentSearchBinding.inflate(inflater, container, false)

        View()
        Listeners()

        return  binding.root
    }

   private fun View(){
       gridmanager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
       binding.searchRecyclerView.layoutManager = gridmanager

       searchAdapter = Search_Adapter(Scontext)
       binding.searchRecyclerView.adapter = searchAdapter
       binding.searchRecyclerView.itemAnimator = null

   }

    private fun Listeners(){

        binding.SearchView.setOnClickListener {
            val query = binding.SearchView.toString()
            if (query.isNotEmpty()){
                searchAdapter.clear()
                search_iamge(query)
            }
            else{
                Toast.makeText(Scontext,"검색어를 입력해주세요.",Toast.LENGTH_SHORT).show()
            }
        }

        val keyboard = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(binding.SearchView.windowToken,0)
    }

    private fun search_iamge(query:String){


    }

}