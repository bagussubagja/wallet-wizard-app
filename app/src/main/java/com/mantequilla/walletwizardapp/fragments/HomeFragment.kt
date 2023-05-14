package com.mantequilla.walletwizardapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mantequilla.walletwizardapp.R
import com.mantequilla.walletwizardapp.adapter.HomeHistoryAdapter
import com.mantequilla.walletwizardapp.databinding.FragmentHomeBinding
import com.mantequilla.walletwizardapp.viewmodel.HistoryTransactionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private val viewModel : HistoryTransactionViewModel by viewModels()
    private lateinit var historyAdapter : HomeHistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.fabToAddTransactionActivity.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addTransactionActivity)
        }
        binding.ivSettingButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
        }
        binding.tvSeeMore.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recentTransactionFragment)
        }
        setUpRecycleView()
        return binding.root
    }

    private fun setUpRecycleView() {
        historyAdapter = HomeHistoryAdapter()
        binding.rvHistoryTransaction.apply {
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        viewModel.responseHistoryTransaction.observe(requireActivity(), {it -> historyAdapter.historyData = it})
    }
}