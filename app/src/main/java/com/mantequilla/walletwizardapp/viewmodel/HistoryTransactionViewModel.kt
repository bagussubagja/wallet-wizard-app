package com.mantequilla.walletwizardapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mantequilla.walletwizardapp.models.history.HistoryTransactionModel
import com.mantequilla.walletwizardapp.repository.HistoryTransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryTransactionViewModel @Inject constructor(
    private val repository: HistoryTransactionRepository
) : ViewModel() {
    private val _response = MutableLiveData<HistoryTransactionModel>()
    val responseHistoryTransaction: LiveData<HistoryTransactionModel>
        get() = _response

    init {
        getHistoryTransaction()
    }

    private fun getHistoryTransaction() = viewModelScope.launch {
        repository.getHistoryTransaction().let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("Error Get History Transaction!", "There's an error in ${response.code()}")
            }
        }
    }
}
