package com.mantequilla.walletwizardapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mantequilla.walletwizardapp.models.BiodataUserModel
import com.mantequilla.walletwizardapp.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BiodataViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    fun sendBiodataUser(
        body: BiodataUserModel,
        onBiodataSuccess: () -> Unit,
        onBiodataFailed: (Throwable) -> Unit
    ) = viewModelScope.launch {
        apiRepository.postBiodataUser(body).let { response ->
            if (response.isSuccessful) {
                Log.d("Response Biodata Success", "${response.code()}")
                onBiodataSuccess()
            } else {
                Log.d("Response Failed Biodata", "${response.code()}")
                Log.d("Response Failed Biodata", "${response.raw()}")
                onBiodataFailed(Throwable("Update Balance Error"))
            }
        }
    }
}