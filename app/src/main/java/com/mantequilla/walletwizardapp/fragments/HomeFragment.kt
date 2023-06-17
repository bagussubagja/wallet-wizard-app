package com.mantequilla.walletwizardapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.mantequilla.walletwizardapp.R
import com.mantequilla.walletwizardapp.adapter.HomeHistoryAdapter
import com.mantequilla.walletwizardapp.databinding.FragmentHomeBinding
import com.mantequilla.walletwizardapp.sharedpreference.PreferenceHelper
import com.mantequilla.walletwizardapp.utils.CommonFunction
import com.mantequilla.walletwizardapp.viewmodel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var multiStateView: MultiStateView
    private lateinit var sharedPref: PreferenceHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        assignUserData()
        sharedPref = PreferenceHelper(requireContext())
        setupRecyclerView()
        multiStateView = binding.multiStateView
        binding.fabToAddTransactionActivity.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addTransactionActivity)
        }
        binding.ivSettingButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
        }
        binding.tvSeeMore.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recentTransactionFragment)
        }
        return binding.root
    }

    private fun assignUserData() {
        viewModel.responseUserData.observe(viewLifecycleOwner) { userData ->
            if (!userData.isNullOrEmpty()) {
                val user = userData[0]
                binding.tvUserName.text = user.name
                binding.tvCurrentBalanceData.text = CommonFunction.formatRupiah(user.balance!!)
                binding.tvOutcomeData.text = CommonFunction.formatRupiah(user.money_spent!!)
                binding.tvIncomeData.text = CommonFunction.formatRupiah(user.income!!)

            }
        }
    }

    private fun setupRecyclerView() {
        val recentActivityAdapterHome = HomeHistoryAdapter(sharedPref = sharedPref)
        binding.rvHistoryTransaction.apply {
            adapter = recentActivityAdapterHome
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.responseHistoryTransaction.observe(viewLifecycleOwner) { historyData ->
            if (historyData.isEmpty()){
                multiStateView.viewState = MultiStateView.ViewState.EMPTY
            } else {
                multiStateView.viewState = MultiStateView.ViewState.CONTENT
                recentActivityAdapterHome.historyData = historyData
            }
            Log.d("History Data", historyData.toString())
        }
    }
}

