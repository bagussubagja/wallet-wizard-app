package com.mantequilla.walletwizardapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mantequilla.walletwizardapp.R
import com.mantequilla.walletwizardapp.databinding.FragmentBiodataBinding
import com.mantequilla.walletwizardapp.databinding.FragmentHomeBinding
import com.mantequilla.walletwizardapp.models.BiodataUserModel
import com.mantequilla.walletwizardapp.sharedpreference.AuthObject
import com.mantequilla.walletwizardapp.sharedpreference.PreferenceHelper
import com.mantequilla.walletwizardapp.viewmodel.BiodataViewModel
import com.musfickjamil.snackify.Snackify
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class BiodataFragment : Fragment() {

    private lateinit var binding: FragmentBiodataBinding
    private lateinit var sharedPrefs: PreferenceHelper
    private val viewModel: BiodataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBiodataBinding.inflate(inflater, container, false)
        binding.materialToolbar.navigationIcon
        sharedPrefs = PreferenceHelper(requireContext())
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
        binding.btnSubmitBiodata.setOnClickListener {
            submitBiodata()
        }
    }

    private fun submitBiodata() {
        val name = binding.etName.text.toString()
        val selectedCurrency = binding.actvDropdownCurrency.text.toString()
        val initialBalance = binding.etNominal.text.toString()
        val params = BiodataUserModel(
            email = sharedPrefs.getString(AuthObject.PREF_EMAIL)!!,
            user_id = sharedPrefs.getString(AuthObject.PREF_ID)!!,
            name = name,
            balance = initialBalance.toInt(),
            currency = selectedCurrency.lowercase(),
            income = 0,
            money_spent = 0
        )
        sharedPrefs.put(AuthObject.PREF_USER_CURRENCY, selectedCurrency)
        viewModel.sendBiodataUser(params, ::onBiodataSuccess, ::onBiodataFailed)
    }

    private fun onBiodataSuccess() {
        sharedPrefs.put(AuthObject.PREF_IS_LOGIN, true)
        Snackify.success(requireView(),"Welcome to Wallet Wizard!", Snackify.LENGTH_LONG ).show()
        findNavController().navigate(R.id.action_biodataFragment_to_app_nav)
    }
    private fun onBiodataFailed(throwable: Throwable) {
        Snackify.error(requireView(),"Error occured!", Snackify.LENGTH_LONG ).show()
    }
}