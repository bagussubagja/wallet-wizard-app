package com.mantequilla.walletwizardapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.mantequilla.walletwizardapp.helper.Constants
import com.mantequilla.walletwizardapp.models.HistoryTransactionModelElement
import com.mantequilla.walletwizardapp.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {
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
}
