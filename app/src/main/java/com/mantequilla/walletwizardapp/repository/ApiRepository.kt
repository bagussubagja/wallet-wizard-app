package com.mantequilla.walletwizardapp.repository

import com.google.gson.JsonObject
import com.mantequilla.walletwizardapp.api.ApiService
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
        body : JsonObject,
        apikey: String
    ) = apiService.addHistoryTransaction(
        body = body,
        apikey = apikey
    )
}