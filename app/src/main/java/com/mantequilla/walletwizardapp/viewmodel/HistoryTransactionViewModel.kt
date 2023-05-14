package com.mantequilla.walletwizardapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mantequilla.walletwizardapp.helper.Constants
import com.mantequilla.walletwizardapp.models.HistoryTransactionModelElement
import com.mantequilla.walletwizardapp.repository.HistoryTransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryTransactionViewModel @Inject constructor(
    private val repository: HistoryTransactionRepository
) : ViewModel() {
    private val _response = MutableLiveData<List<HistoryTransactionModelElement>>()
    val responseHistoryTransaction: LiveData<List<HistoryTransactionModelElement>>
        get() = _response
    init {
        getHistoryTransaction(
            "Bearer ${Constants.API_KEY}",
            "*",
             Constants.API_KEY,
            "eq.a26ddfd8-077e-4e7f-abd7-c12d5b7a6088"
        )
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
                _response.postValue(response.body())
                Log.d("Success!", "Data ${response.body()}")
            } else {
                Log.d("Error Get History Transaction!", "There's an error in ${response.raw()}")
            }
        }
    }
}
