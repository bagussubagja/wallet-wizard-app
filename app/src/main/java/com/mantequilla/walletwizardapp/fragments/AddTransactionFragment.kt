package com.mantequilla.walletwizardapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.gson.JsonObject
import com.mantequilla.walletwizardapp.R
import com.mantequilla.walletwizardapp.databinding.FragmentAddTransactionBinding
import com.mantequilla.walletwizardapp.models.HistoryTransactionModelElement
import com.mantequilla.walletwizardapp.viewmodel.AddTransactionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTransactionFragment : Fragment() {
    private lateinit var binding : FragmentAddTransactionBinding
    private  val viewModel : AddTransactionViewModel by viewModels()
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
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown_text, transactionType)

        binding.actvDropdownTransactionType.setAdapter(arrayAdapter)
        binding.btnSubmitAddTransaction.setOnClickListener {
            addTransactionHistoryBody()
        }
    }

    private fun addTransactionHistoryBody (){
        val titleText = binding.etTitle.text.toString()
        val nominalText = binding.etNominal.text.toString()
        val selectedTransactionType = binding.actvDropdownTransactionType.text.toString()
        val jsonBody = JsonObject().apply {
            addProperty("user_id", "a26ddfd8-077e-4e7f-abd7-c12d5b7a6088")
            addProperty("title", titleText)
            addProperty("nominal", nominalText.toInt())
            addProperty("is_credit", selectedTransactionType.uppercase() == "CREDIT")
            addProperty("currency", "Rupiah")
        }
        viewModel.addTransactionHistoryData(jsonBody)
    }
}