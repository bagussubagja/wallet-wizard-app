package com.mantequilla.walletwizardapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mantequilla.walletwizardapp.R
import com.mantequilla.walletwizardapp.databinding.FragmentBiodataBinding
import com.mantequilla.walletwizardapp.databinding.FragmentHomeBinding

class BiodataFragment : Fragment() {

    private lateinit var binding: FragmentBiodataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBiodataBinding.inflate(inflater, container, false)
        binding.materialToolbar.navigationIcon
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.materialToolbar)
        binding.materialToolbar.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.round_arrow_back_24)
        binding.materialToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        val currencyType = resources.getStringArray(R.array.currencyType)
        val arrayAdapter =
            ArrayAdapter(requireContext(), R.layout.item_dropdown_text, currencyType)
        binding.actvDropdownCurrency.setAdapter(arrayAdapter)
    }
}