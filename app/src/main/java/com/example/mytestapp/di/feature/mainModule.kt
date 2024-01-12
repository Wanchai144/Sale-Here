package com.example.mytestapp.di.feature

import com.example.mytestapp.presentation.feature.achievement.AchievementViewModel
import com.example.mytestapp.presentation.feature.goal.ViewGoalViewModel
import com.example.mytestapp.presentation.feature.main.HomeMainViewModel
import com.example.mytestapp.presentation.feature.socket.SocketViewModel
import com.example.mytestapp.presentation.feature.viewmodel.share.ShareMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {

    viewModel {
        HomeMainViewModel()
    }

    viewModel {
        ShareMainViewModel()
    }

    viewModel {
        AchievementViewModel()
    }

    viewModel {
        ViewGoalViewModel()
    }

    viewModel {
        SocketViewModel(socketManager = get())
    }






}