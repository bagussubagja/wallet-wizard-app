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
import com.mantequilla.walletwizardapp.databinding.FragmentAddTransactionBinding
class AddTransactionFragment : Fragment() {
    private lateinit var binding : FragmentAddTransactionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTransactionBinding.inflate(inflater, container, false)
        binding.materialToolbar.navigationIcon
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.materialToolbar)
        binding.materialToolbar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.round_arrow_back_24)
        binding.materialToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        val transactionType = resources.getStringArray(R.array.transactionType)
        var arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown_text, transactionType)
        binding.actvDropdownTransactionType.setAdapter(arrayAdapter)
    }
}
