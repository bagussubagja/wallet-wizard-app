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
import com.mantequilla.walletwizardapp.databinding.FragmentLoginBinding
import com.mantequilla.walletwizardapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    private val viewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLoginAction.setOnClickListener {
            loginBody(
                email = binding.etEmailAddress.text.toString(),
                password = binding.etPassword.text.toString()
            )
        }
        binding.btnLoginToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun loginBody(email: String, password: String) {
        val body = JsonObject().apply {
            addProperty("email", email)
            addProperty("password", password)
        }
        viewModel.loginAction(body, ::onLoginSuccess, ::onLoginFailure)
    }

    private fun onLoginSuccess() {
        findNavController().navigate(R.id.action_loginFragment_to_app_nav)
    }

    private fun onLoginFailure(error: Throwable) {

    }
}