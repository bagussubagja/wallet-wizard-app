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
import androidx.navigation.fragment.findNavController
import com.google.gson.JsonObject
import com.mantequilla.walletwizardapp.R
import com.mantequilla.walletwizardapp.databinding.FragmentAddTransactionBinding
import com.mantequilla.walletwizardapp.models.HistoryTransactionModelElement
import com.mantequilla.walletwizardapp.sharedpreference.AuthObject
import com.mantequilla.walletwizardapp.sharedpreference.PreferenceHelper
import com.mantequilla.walletwizardapp.utils.CommonFunction
import com.mantequilla.walletwizardapp.viewmodel.AddTransactionViewModel
import com.musfickjamil.snackify.Snackify
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTransactionFragment : Fragment() {
    private lateinit var binding : FragmentAddTransactionBinding
    private lateinit var sharedPref: PreferenceHelper
    private val viewModel : AddTransactionViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTransactionBinding.inflate(inflater, container, false)
        binding.materialToolbar.navigationIcon
        sharedPref = PreferenceHelper(requireContext())
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
        binding.tvCurrentDate.text = CommonFunction.getCurrentDate()
        binding.tvUserCurrency.text = sharedPref.getString(AuthObject.PREF_USER_CURRENCY)?.replaceFirstChar(Char::titlecase)
        binding.actvDropdownTransactionType.setAdapter(arrayAdapter)
        binding.btnSubmitAddTransaction.setOnClickListener {
            if(binding.etTitle.text.isNullOrEmpty() &&
                binding.etNominal.text.isNullOrEmpty()) {
                Snackify.warning(requireView(), "The Fields cant be empty!", Snackify.LENGTH_LONG).show()
            } else {
                addTransactionHistoryBody()
                updateBalance()
            }

        }
    }

    private fun addTransactionHistoryBody (){
        val titleText = binding.etTitle.text.toString()
        val nominalText = binding.etNominal.text.toString()
        val nominal = nominalText.toIntOrNull()
        val selectedTransactionType = binding.actvDropdownTransactionType.text.toString()
        val body = HistoryTransactionModelElement(
            null,
            user_id = sharedPref.getString(AuthObject.PREF_ID),
            title = titleText,
            nominal = nominal,
            is_credit = selectedTransactionType.uppercase() == "CREDIT",
            currency = sharedPref.getString(AuthObject.PREF_USER_CURRENCY),
            date = null
        )
        viewModel.addTransactionHistoryData(body, ::onAddTransactionSuccess, ::onAddTransactionFailed)
    }

    private fun onAddTransactionSuccess() {
        binding.etTitle.text?.clear()
        binding.etNominal.text?.clear()
        Snackify.success(requireView(), "Transaction Success!", Snackify.LENGTH_LONG).show()
        findNavController().navigate(R.id.homeFragment)
    }
    private fun onAddTransactionFailed(e: Throwable) {}

    private fun updateBalance() {
        val nominalText = binding.etNominal.text.toString()
        val nominal = nominalText.toInt()
        val totalNominal = sharedPref.getInt(AuthObject.PREF_USER_BALANCE) - nominal
        val nominalBody = JsonObject().apply {
            addProperty("balance", totalNominal)
        }
        viewModel.updateBalanceData(nominalBody, ::onUpdateBalanceSuccess, ::onUpdateBalanceFailed)
    }

    private fun onUpdateBalanceSuccess() {
        Log.d("Success Update Balance", "Success Update Balance")
    }
    private fun onUpdateBalanceFailed(e: Throwable) {
        Log.d("Failed Update Balance", e.toString())
    }
}