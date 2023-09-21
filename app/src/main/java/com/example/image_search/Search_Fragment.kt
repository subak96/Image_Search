package com.example.image_search

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.image_search.Constants.KEY
import com.example.image_search.Constants.NAME
import com.example.image_search.Ret_Client.Api
import com.example.image_search.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class Search_Fragment : Fragment() {

    private lateinit var Scontext: Context
    private lateinit var binding : FragmentSearchBinding
    private lateinit var searchAdapter: Search_Adapter
    private lateinit var gridmanager : StaggeredGridLayoutManager

private var search_Item: ArrayList<Search_Item> = ArrayList()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Scontext = context
    }
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

//    fun savesearch (context: Context, query: String){
//        val save = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
//        save.edit().putString(KEY, query).apply()
//    }
//    fun getsearch (context: Context): String? {
//        val get = context.getSharedPreferences(NAME,Context.MODE_PRIVATE )
//        return get.getString(KEY, null)
//    }

   private fun View(){
       gridmanager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
       binding.searchRecyclerView.layoutManager = gridmanager
       searchAdapter = Search_Adapter(Scontext)
       binding.searchRecyclerView.adapter = searchAdapter
       binding.searchRecyclerView.itemAnimator = null


//       val lastgetsearch = getsearch(requireContext())
//       binding.searchEditText.setText(lastgetsearch)

   }

    private fun Listeners(){

        binding.searchText.setOnClickListener {
            val query = binding.searchEditText.text.toString()
            if (query.isNotEmpty()){
//                savesearch(requireContext(), query)
                searchAdapter.clear()
                search_iamge(query)
            }
            else{
                Toast.makeText(Scontext,"검색어를 입력해주세요.",Toast.LENGTH_SHORT).show()
            }
        }

        val keyboard = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(binding.searchEditText.windowToken,0)
    }

    private fun search_iamge(query: String) {
        Api.search(Constants.AUTH_HEADER, query, "recency", 1, 80)
            ?.enqueue(object : Callback<ApiModel?> {
                override fun onResponse(call: Call<ApiModel?>, response: Response<ApiModel?>) {
                    response.body()?.meta?.let { meta ->
                        if (meta.total_count > 0) {
                            response.body()!!.documents.forEach { document ->
                                val title = document.display_sitename
                                val datetime = document.datetime
                                val url = document.thumbnail_url
                                search_Item.add(Search_Item(title, datetime, url))
                            }
                        }
                    }
                    searchAdapter.Item = search_Item
                    searchAdapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<ApiModel?>, t: Throwable) {
                }
            })
    }
}