package com.example.mytestapp.presentation.feature.goal

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.databinding.FragmentAchievementBinding
import com.example.mytestapp.databinding.FragmentViewGoalBinding
import com.example.mytestapp.presentation.feature.achievement.AchievementViewModel
import com.example.mytestapp.presentation.feature.adapter.AdapterAchievement
import com.example.mytestapp.presentation.feature.adapter.AdapterGoal
import com.example.mytestapp.presentation.feature.viewmodel.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewGoalFragment: BaseFragment(){
    private val binding by lazy { FragmentViewGoalBinding.inflate(layoutInflater) }
    private val vm: ViewGoalViewModel by viewModel()

    private val adapter: AdapterGoal by lazy {
        AdapterGoal(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        callbackAdapter()
        initRecyclerView()

    }

    private fun callbackAdapter(){

    }

    private fun initView()  = with(binding){
        adapter.loadData(vm.mockDataGoal())
    }


    private fun initRecyclerView() = with(binding) {
        val gridLayoutManager = GridLayoutManager(rvGoal.context, 3, RecyclerView.VERTICAL, false)
        rvGoal.layoutManager = gridLayoutManager
        rvGoal.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}