package com.example.mytestapp.presentation.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.MainActivity
import com.example.mytestapp.R
import com.example.mytestapp.databinding.FragmentHomeMainBinding
import com.example.mytestapp.presentation.feature.adapter.AdapterProduct
import com.example.mytestapp.presentation.feature.adapter.AdapterSection
import com.example.mytestapp.presentation.feature.viewmodel.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeMainFragment : BaseFragment(){
    private val binding by lazy { FragmentHomeMainBinding.inflate(layoutInflater) }
    private val viewModel: HomeMainViewModel by viewModel()

    private val adapter: AdapterProduct by lazy {
        AdapterProduct(requireContext())
    }

    private val activity by lazy {
        (requireActivity() as MainActivity)
    }
    private val adapterSection: AdapterSection by lazy {
        AdapterSection(requireContext())
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
        observeViewModel()
        initRecyclerView()
    }

    private fun initView()  = with(binding){
        btnNewGoal.setOnClickListener {
            activity.goneAndVisibleBottomManu(false)
            findNavController().navigate(R.id.action_homeMainFragment_to_viewGoalFragment)
        }
    }

    private fun observeViewModel() = with(viewModel){
        val bannerOfferData = mockDataBannerOffer()
        val bannerSuggestData = mockDataBannerSuggest()
        adapterSection.loadData(listOf(bannerOfferData,bannerSuggestData))
        adapter.loadData(mockDataProduct())

    }



    private fun initRecyclerView() = with(binding) {
        rvProduct.layoutManager =
            LinearLayoutManager(rvProduct.context, RecyclerView.HORIZONTAL, false)
        rvProduct.adapter = adapter

        rvSection.layoutManager =
            LinearLayoutManager(rvSection.context, RecyclerView.VERTICAL, false)
        rvSection.adapter = adapterSection
    }

    override fun onResume() {
        activity.goneAndVisibleBottomManu(true)
        super.onResume()
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}