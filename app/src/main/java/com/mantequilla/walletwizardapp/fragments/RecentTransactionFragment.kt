package com.mantequilla.walletwizardapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mantequilla.walletwizardapp.R
import com.mantequilla.walletwizardapp.databinding.FragmentRecentTransactionBinding

class RecentTransactionFragment : Fragment() {
    private lateinit var binding : FragmentRecentTransactionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecentTransactionBinding.inflate(inflater, container, false)
        binding.materialToolbarRecentActivity.navigationIcon
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.materialToolbarRecentActivity)
        binding.materialToolbarRecentActivity.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.round_arrow_back_24)
        binding.materialToolbarRecentActivity.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }


}