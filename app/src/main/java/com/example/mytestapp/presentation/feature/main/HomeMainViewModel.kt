package com.example.mytestapp.presentation.feature.main

import com.example.mytestapp.R
import com.example.mytestapp.data.model.DataBannerOffer
import com.example.mytestapp.data.model.DataBannerSuggest
import com.example.mytestapp.data.model.DataItemProduct
import com.example.mytestapp.presentation.feature.base.BaseViewModel
import com.example.mytestapp.presentation.feature.base.ViewEffect


class HomeMainViewModel() :
    BaseViewModel<Any, ViewEffect>() {

    fun mockDataProduct(): List<DataItemProduct> {
        return listOf(
            DataItemProduct(
                titlePrice = "16,500",
                subPrice = "20,000",
                review = 70,
                name = "เที่ยวญี่ปุ่น",
                status = "Good",
                timer = "45 days left",
                image = R.drawable.cartoon
            ),
            DataItemProduct(
                titlePrice = "500",
                subPrice = "6,000",
                review = 30,
                name = "ซื้อกองทุนรวม",
                status = "Unhappy",
                timer = "20 days left",
                image = R.drawable.cartoon
            ),
            DataItemProduct(
                titlePrice = "1000",
                subPrice = "1000",
                review = 50,
                name = "ไปทะเล",
                status = "Good",
                timer = "45 days left",
                image = R.drawable.cartoon
            )
        )
    }

    fun mockDataBannerOffer(): DataBannerOffer {
        return DataBannerOffer(image = listOf( R.drawable.cartoon,R.drawable.cartoon,R.drawable.cartoon))
    }

    fun mockDataBannerSuggest(): DataBannerSuggest {
        return DataBannerSuggest(image = listOf( R.drawable.cartoon,R.drawable.cartoon,R.drawable.cartoon))
    }

}