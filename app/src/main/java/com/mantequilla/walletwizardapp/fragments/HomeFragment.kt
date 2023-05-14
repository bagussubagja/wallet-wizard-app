package com.mantequilla.walletwizardapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mantequilla.walletwizardapp.R
import com.mantequilla.walletwizardapp.adapter.HomeHistoryAdapter
import com.mantequilla.walletwizardapp.databinding.FragmentHomeBinding
import com.mantequilla.walletwizardapp.viewmodel.HistoryTransactionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HistoryTransactionViewModel by viewModels()
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val recentActivityAdapterHome = HomeHistoryAdapter()
        binding.rvHistoryTransaction.apply {
            adapter = recentActivityAdapterHome
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.responseHistoryTransaction.observe(viewLifecycleOwner) { historyData ->
            recentActivityAdapterHome.historyData = historyData
        }
    }
}

