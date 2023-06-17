package com.mantequilla.walletwizardapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.mantequilla.walletwizardapp.helper.Constants
import com.mantequilla.walletwizardapp.models.HistoryTransactionModelElement
import com.mantequilla.walletwizardapp.models.UserModel
import com.mantequilla.walletwizardapp.repository.ApiRepository
import com.mantequilla.walletwizardapp.sharedpreference.AuthObject
import com.mantequilla.walletwizardapp.sharedpreference.PreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repository: ApiRepository,
    private val sharedPref: PreferenceHelper
) : ViewModel() {
    private val _historyResponse = MutableLiveData<List<HistoryTransactionModelElement>>()
    private val _userDataResponse = MutableLiveData<List<UserModel>>()
    val responseHistoryTransaction: LiveData<List<HistoryTransactionModelElement>>
        get() = _historyResponse
    val responseUserData: LiveData<List<UserModel>>
        get() = _userDataResponse
    init {
        getHistoryTransaction(
            "Bearer ${Constants.API_KEY}",
            "*",
             Constants.API_KEY,
            "eq.${sharedPref.getString(AuthObject.PREF_ID)}"
        )
        getUserData(
            "Bearer ${Constants.API_KEY}",
            "*",
            Constants.API_KEY,
            "eq.${sharedPref.getString(AuthObject.PREF_ID)}"
        )
    }

    private fun getUserData(
        authHeader: String,
        fields : String,
        apikey: String,
        userId: String
    ) = viewModelScope.launch {
        repository.getUserData(
            authHeader,
            fields,
            apikey,
            userId
        ).let { response ->
            if(response.isSuccessful) {
                _userDataResponse.postValue(response.body())
                response.body()?.get(0)?.currency?.let {value ->
                    sharedPref.put(AuthObject.PREF_USER_CURRENCY,
                        value
                    )
                }
            }else {
                Log.d("Error Get User Data!", "There's an error in ${response.raw()}")
            }
        }
    }

    private fun getHistoryTransaction(
        authHeader: String,
        fields : String,
        apikey: String,
        userId: String
    ) = viewModelScope.launch {
        repository.getHistoryTransaction(
            authHeader,
            fields,
            apikey,
            userId
        ).let { response ->
            if (response.isSuccessful) {
                _historyResponse.postValue(response.body())
                Log.d("Success!!!", "Data ${response.headers()}")
            } else {
                Log.d("Error Get History Transaction!", "There's an error in ${response.raw()}")
            }
        }
    }
}
