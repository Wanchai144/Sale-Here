package com.example.mytestapp.presentation.feature.achievement

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.databinding.FragmentAchievementBinding
import com.example.mytestapp.databinding.FragmentHomeMainBinding
import com.example.mytestapp.presentation.feature.adapter.AdapterAchievement
import com.example.mytestapp.presentation.feature.adapter.AdapterProduct
import com.example.mytestapp.presentation.feature.adapter.AdapterSection
import com.example.mytestapp.presentation.feature.main.HomeMainViewModel
import com.example.mytestapp.presentation.feature.viewmodel.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AchievementFragment : BaseFragment(){
    private val binding by lazy { FragmentAchievementBinding.inflate(layoutInflater) }
    private val vm: AchievementViewModel by viewModel()

    private val adapter: AdapterAchievement by lazy {
        AdapterAchievement(requireContext())
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
        initRecyclerView()
    }


    private fun initView()  = with(binding){
        adapter.loadData(vm.mockDataAchievement())
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