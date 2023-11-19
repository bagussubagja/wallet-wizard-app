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
class RegisterViewModel @Inject constructor(
    private val repository: ApiRepository,
    private val sharedPref: PreferenceHelper
) : ViewModel() {
    fun registerAction(
        body: AuthBody,
        onRegisterSuccess: () -> Unit,
        onRegisterFailed: (Throwable) -> Unit
    ) = viewModelScope.launch {
        repository.authenticationRegister(body, Constants.API_KEY).let { response ->
            if (response.isSuccessful) {
                val responseBody = response.body().toString()
                val jsonResponse = JSONObject(responseBody)
                val userObject = jsonResponse.getJSONObject("user")
                val userId = userObject.getString("id")
                Log.d("Register Success", "User ID: $userId")
                sharedPref.put(AuthObject.PREF_ID, userId)
                sharedPref.put(AuthObject.PREF_EMAIL, body.email)
                onRegisterSuccess()
            } else {
                Log.d("Register Failed", "${response.raw()}")
                onRegisterFailed(Throwable("Login Failed"))
            }
        }
    }
}