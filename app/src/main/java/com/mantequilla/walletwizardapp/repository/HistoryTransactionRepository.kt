package com.mantequilla.walletwizardapp.repository

import com.mantequilla.walletwizardapp.api.ApiService
import javax.inject.Inject

class HistoryTransactionRepository
@Inject
constructor(private val apiService: ApiService)
{
    suspend fun getHistoryTransaction() = apiService.getHistoryTransaction()
}