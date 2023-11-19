package com.mantequilla.walletwizardapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.mantequilla.walletwizardapp.helper.Constants
import com.mantequilla.walletwizardapp.models.AuthBody
import com.mantequilla.walletwizardapp.repository.ApiRepository
import com.mantequilla.walletwizardapp.sharedpreference.AuthObject
import com.mantequilla.walletwizardapp.sharedpreference.PreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: ApiRepository,
    private val sharedPref: PreferenceHelper
    ) : ViewModel() {
    fun loginAction (
        body: AuthBody,
        onLoginSuccess: () -> Unit,
        onLoginFailed: (Throwable) -> Unit,
    ) = viewModelScope.launch {
        repository.authenticationLogin(
            body = body,
            apikey = Constants.API_KEY,
            grant_type = "password"
        ).let { response ->
            if(response.isSuccessful) {
                val responseBody = response.body().toString()
                val jsonResponse = JSONObject(responseBody)
                val userObject = jsonResponse.getJSONObject("user")
                val userId = userObject.getString("id")
                Log.d("Login Success", "User ID: $userId")
                sharedPref.put(AuthObject.PREF_ID, userId)
                onLoginSuccess()
            }else {
                Log.d("Login Failed", "${response.raw()}")
                onLoginFailed(Throwable("Login Failed"))
            }
        }
    }
}