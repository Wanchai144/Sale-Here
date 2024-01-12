package com.example.mytestapp.presentation.feature.goal

import androidx.lifecycle.ViewModel
import com.example.mytestapp.R
import com.example.mytestapp.data.model.DataItemGrid

class ViewGoalViewModel : ViewModel() {
    
    
    fun mockDataGoal(): List<DataItemGrid> {
        return listOf(
            DataItemGrid(
                title = "Travel",
                image = R.drawable.achievement
            ),
            DataItemGrid(
                title = "Education",
                image = R.drawable.achievement
            ),
            DataItemGrid(
                title = "invest",
                image = R.drawable.achievement
            ),
            DataItemGrid(
                title = "Clothing",
                image = R.drawable.achievement
            ),
            DataItemGrid(
                title = "Education",
                image = R.drawable.achievement
            ),
        )
    }
}