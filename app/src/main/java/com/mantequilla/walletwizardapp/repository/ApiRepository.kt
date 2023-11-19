package com.mantequilla.walletwizardapp.repository

import com.google.gson.JsonObject
import com.mantequilla.walletwizardapp.api.ApiService
import com.mantequilla.walletwizardapp.models.AuthBody
import com.mantequilla.walletwizardapp.models.BiodataUserModel
import com.mantequilla.walletwizardapp.models.HistoryTransactionModelElement
import javax.inject.Inject

class ApiRepository
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

    suspend fun getUserData(
        authHeader: String,
        fields : String,
        apikey: String,
        userId: String
    ) = apiService.getUserData(
        authHeader = authHeader,
        fields = fields,
        apiKey = apikey,
        userId = userId,
    )

    suspend fun addHistoryTransaction(
        body : HistoryTransactionModelElement,
        apikey: String
    ) = apiService.addHistoryTransaction(
        body = body,
        apikey = apikey
    )

    suspend fun authenticationLogin(
        body: AuthBody,
        apikey: String,
        grant_type: String
    ) = apiService.authenticationLogin(
        grant_type = grant_type,
        apikey = apikey,
        body = body
    )

    suspend fun updateBalance(
        userId: String,
        apiKey: String,
        balance: JsonObject
    ) = apiService.updateBalance(
        userId = userId,
        apiKey = apiKey,
        balance = balance
    )

    suspend fun updateIncomeOutcome(
        userId: String,
        apiKey: String,
        nominal: JsonObject
    ) = apiService.updateIncomeOutcome(
        userId, apiKey, nominal
    )

    suspend fun authenticationRegister(
        body: AuthBody,
        apiKey: String
    ) = apiService.authenticationRegister(body, apiKey)

    suspend fun postBiodataUser(
        body: BiodataUserModel
    ) = apiService.postBiodataUser(body)
}