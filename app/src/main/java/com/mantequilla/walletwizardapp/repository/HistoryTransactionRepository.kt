package com.mantequilla.walletwizardapp.repository

import com.mantequilla.walletwizardapp.api.ApiService
import javax.inject.Inject

class HistoryTransactionRepository
@Inject
constructor(private val apiService: ApiService)
{
    suspend fun getHistoryTransaction(
        authHeader: String,
        fields : String,
        apikey: String,
        userId: String
    ) = apiService.getHistoryTransaction(
        authHeader = authHeader,
        fields = fields,
        apiKey = apikey,
        userId = userId,
    )
}