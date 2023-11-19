package com.mantequilla.walletwizardapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.gson.JsonObject
import com.mantequilla.walletwizardapp.R
import com.mantequilla.walletwizardapp.databinding.FragmentRegisterBinding
import com.mantequilla.walletwizardapp.models.AuthBody
import com.mantequilla.walletwizardapp.sharedpreference.AuthObject
import com.mantequilla.walletwizardapp.sharedpreference.PreferenceHelper
import com.mantequilla.walletwizardapp.viewmodel.RegisterViewModel
import com.musfickjamil.snackify.Snackify
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var sharedPref: PreferenceHelper
    private lateinit var binding : FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        sharedPref = PreferenceHelper(requireContext())
        binding.btnRegistertoLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.btnRegisterAction.setOnClickListener {
            registerAction(
                email = binding.etEmailAddress.text.toString(),
                password = binding.etPassword.text.toString()
            )
        }
        return binding.root
    }

    private fun registerAction(email: String, password: String) {
        val authRegisterBody = AuthBody(
            email = email,
            password = password
        )
        viewModel.registerAction(authRegisterBody, ::onRegisterSuccess, ::onRegisterFailed)
    }

    private fun onRegisterSuccess() {
        findNavController().navigate(R.id.action_registerFragment_to_biodataFragment)
        Snackify.success(requireView(),"Your account has been successfully registered!", Snackify.LENGTH_LONG ).show()
    }
    private fun onRegisterFailed(throwable: Throwable) {
        Snackify.error(requireView(),"This email has already registered!", Snackify.LENGTH_LONG ).show()
    }
}