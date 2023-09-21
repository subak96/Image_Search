package com.example.image_search

data class ApiModel (

    val documents:ArrayList<Documents>,

    val meta:Meta

        ){

    data class Documents(

        val collection: String,

        val thumbnail_url: String,

        val image_url : String,

        val width : Int,

        val height: Int,

        val display_sitename: String,

        val doc_url: String,

        val datetime: String
    )

    data class Meta(

        val total_count: Int,

        val is_end: Boolean,

        val pageable_count: Int
    )
}