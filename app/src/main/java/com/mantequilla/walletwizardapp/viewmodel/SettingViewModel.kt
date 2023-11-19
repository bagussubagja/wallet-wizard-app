package com.mantequilla.walletwizardapp.viewmodel

import androidx.lifecycle.ViewModel
import com.mantequilla.walletwizardapp.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(apiRepository: ApiRepository) : ViewModel() {
}