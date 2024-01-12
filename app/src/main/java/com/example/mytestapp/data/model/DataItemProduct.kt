package com.example.mytestapp.data.model

data class DataItemProduct(
    val titlePrice: String?,
    val subPrice:String?,
    val review: Int?,
    val name: String?,
    val status: String?,
    val timer: String?,
    val image:Int?
)


data class DataBannerOffer(
    val image:List<Int>?
)


data class DataBannerSuggest(
    val image:List<Int>?
)


data class DataItemGrid(
    val title: String?,
    val image:Int?
)

