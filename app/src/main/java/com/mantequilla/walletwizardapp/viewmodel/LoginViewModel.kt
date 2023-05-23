package com.mantequilla.walletwizardapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.mantequilla.walletwizardapp.helper.Constants
import com.mantequilla.walletwizardapp.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {
    fun loginAction (
        body: JsonObject,
        onLoginSuccess: () -> Unit,
        onLoginFailed: (Throwable) -> Unit,
    ) = viewModelScope.launch {
        repository.authenticationLogin(
            body = body,
            apikey = Constants.API_KEY,
            grant_type = "password"
        ).let { response ->
            if(response.isSuccessful) {
                Log.d("Login Success", "${response.body()}")
                onLoginSuccess()
            }else {
                Log.d("Login Failed", "${response.raw()}")
                onLoginFailed(Throwable("Login Failed"))
            }
        }
    }
}