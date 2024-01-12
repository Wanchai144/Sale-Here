package com.example.mytestapp.presentation.feature.achievement


import com.example.mytestapp.R
import com.example.mytestapp.data.model.DataItemGrid
import com.example.mytestapp.presentation.feature.base.BaseViewModel
import com.example.mytestapp.presentation.feature.base.ViewEffect

class AchievementViewModel() : BaseViewModel<Any, ViewEffect>() {


    fun mockDataAchievement(): List<DataItemGrid> {
        return listOf(
            DataItemGrid(
                title = "Achievement",
                image = R.drawable.achievement
            ),
            DataItemGrid(
                title = "Achievement",
                image = R.drawable.achievement
            ),
            DataItemGrid(
                title = "Achievement",
                image = R.drawable.achievement
            ),
            DataItemGrid(
                title = "Achievement",
                image = R.drawable.achievement
            ),
            DataItemGrid(
                title = "Achievement",
                image = R.drawable.achievement
            ),
            DataItemGrid(
                title = "Achievement",
                image = R.drawable.achievement
            ),
            DataItemGrid(
                title = "Achievement",
                image = R.drawable.achievement
            ),
            DataItemGrid(
                title = "Achievement",
                image = R.drawable.achievement
            ),
        )
    }
}