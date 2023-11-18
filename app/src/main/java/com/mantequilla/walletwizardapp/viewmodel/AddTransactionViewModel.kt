package com.mantequilla.walletwizardapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.mantequilla.walletwizardapp.helper.Constants
import com.mantequilla.walletwizardapp.models.HistoryTransactionModelElement
import com.mantequilla.walletwizardapp.repository.ApiRepository
import com.mantequilla.walletwizardapp.sharedpreference.AuthObject
import com.mantequilla.walletwizardapp.sharedpreference.PreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val repository: ApiRepository,
    private val sharedPref: PreferenceHelper
    ) : ViewModel() {
    fun addTransactionHistoryData(
        body: HistoryTransactionModelElement,
        onAddTransactionSuccess: () -> Unit,
        onAddTransactionFailed: (Throwable) -> Unit,
    ) = viewModelScope.launch {
        repository.addHistoryTransaction(
            body = body,
            apikey = Constants.API_KEY
        ).let { response ->
            if(response.isSuccessful) {
                Log.d("Add Transaction History Success!", "${response.raw()}")
                onAddTransactionSuccess()
            } else {
                Log.d("Add Transaction History Failed", "${response.raw()}")
                onAddTransactionFailed(Throwable("Transaction Failed"))
            }
        }
    }

    fun updateBalanceData(
        balance: JsonObject,
        onUpdateBalanceSuccess: () -> Unit,
        onUpdateBalanceFailed: (Throwable) -> Unit,
    ) = viewModelScope.launch {
        repository.updateBalance(
            balance = balance,
            apiKey = Constants.API_KEY,
            userId = "eq.${sharedPref.getString(AuthObject.PREF_ID)}"
        ).let { response ->
            if(response.isSuccessful) {
                Log.d("Response Success Update Balance", "${response.code()}")
                onUpdateBalanceSuccess()
            } else {
                Log.d("Response Failed Update Balance", "${response.code()}")
                Log.d("Response Failed Update Balance", "${response.raw()}")
                onUpdateBalanceFailed(Throwable("Update Balance Error"))
            }
        }
    }
}
