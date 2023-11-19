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
import com.mantequilla.walletwizardapp.models.AuthBody
import com.mantequilla.walletwizardapp.sharedpreference.AuthObject
import com.mantequilla.walletwizardapp.sharedpreference.PreferenceHelper
import com.mantequilla.walletwizardapp.viewmodel.LoginViewModel
import com.musfickjamil.snackify.Snackify
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var sharedPref: PreferenceHelper
    private lateinit var binding : FragmentLoginBinding
    private val viewModel : LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        sharedPref = PreferenceHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLoginAction.setOnClickListener {
            if(binding.etEmailAddress.text.isNullOrEmpty() && binding.etPassword.text.isNullOrEmpty()){
                Snackify.warning(requireView(),"Make sure you enter your email and password correctly", Snackify.LENGTH_LONG ).show()
            } else {
                loginBody(
                    email = binding.etEmailAddress.text.toString(),
                    password = binding.etPassword.text.toString()
                )
            }

        }
        binding.btnLoginToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun loginBody(email: String, password: String) {
        val loginBody = AuthBody(
            email = email,
            password = password
        )
        viewModel.loginAction(loginBody, ::onLoginSuccess, ::onLoginFailure)
    }

    private fun onLoginSuccess() {
        sharedPref.put(AuthObject.PREF_IS_LOGIN, true)
        findNavController().navigate(R.id.action_loginFragment_to_app_nav)
    }

    private fun onLoginFailure(error: Throwable) {
        Snackify.error(requireView(),"Your email or password are incorrect!", Snackify.LENGTH_LONG ).show()
    }
}