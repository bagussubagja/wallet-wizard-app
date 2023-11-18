package com.mantequilla.walletwizardapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.mantequilla.walletwizardapp.R
import com.mantequilla.walletwizardapp.databinding.FragmentSettingBinding
import com.mantequilla.walletwizardapp.sharedpreference.AuthObject
import com.mantequilla.walletwizardapp.sharedpreference.PreferenceHelper

class SettingFragment : Fragment() {
    private lateinit var binding : FragmentSettingBinding
    private lateinit var sharedPref: PreferenceHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        binding.materialToolbarSetting.navigationIcon
        sharedPref = PreferenceHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.materialToolbarSetting)
        binding.materialToolbarSetting.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.round_arrow_back_24)
        binding.materialToolbarSetting.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.tvUserFullNameSetting.text = sharedPref.getString(AuthObject.PREF_NAME)
        binding.tvUserEmailSetting.text = sharedPref.getString(AuthObject.PREF_EMAIL)
        binding.logoutMenu.setOnClickListener {
            doLogoutAction()
        }
        binding.topUpBalanceMenu.setOnClickListener {  }
    }

    private fun doLogoutAction() {
        sharedPref.clear()
        findNavController().navigate(R.id.action_settingFragment_to_logout_nav)
    }

}