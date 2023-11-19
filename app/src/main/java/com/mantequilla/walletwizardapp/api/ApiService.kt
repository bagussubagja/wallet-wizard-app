package com.mantequilla.walletwizardapp.api

import com.google.gson.JsonObject
import com.mantequilla.walletwizardapp.helper.Constants
import com.mantequilla.walletwizardapp.models.AuthBody
import com.mantequilla.walletwizardapp.models.BiodataUserModel

import com.mantequilla.walletwizardapp.models.HistoryTransactionModelElement
import com.mantequilla.walletwizardapp.models.UserModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.END_POINT_HISTORY_TRANSACTION)
    suspend fun getHistoryTransaction(
        @Header("Authorization") authHeader: String,
        @Query("select") fields: String,
        @Query("apikey") apiKey: String,
        @Query("user_id") userId: String
    ) : Response<List<HistoryTransactionModelElement>>

    @GET(Constants.END_POINT_USER)
    suspend fun getUserData(
        @Header("Authorization") authHeader: String,
        @Query("select") fields: String,
        @Query("apikey") apiKey: String,
        @Query("user_id") userId: String
    ) : Response<List<UserModel>>

    @Headers("${Constants.AUTHORIZATION}:Bearer ${Constants.API_KEY}")
    @POST(Constants.END_POINT_HISTORY_TRANSACTION)
    suspend fun addHistoryTransaction(
        @Body body : HistoryTransactionModelElement,
        @Query("apikey") apikey: String = Constants.API_KEY
    ) : Response<Void>

    @Headers("${Constants.AUTHORIZATION}:Bearer ${Constants.API_KEY}")
    @POST(Constants.END_POINT_AUTH)
    suspend fun authenticationLogin(
        @Query("grant_type") grant_type: String,
        @Query("apikey") apikey: String,
        @Body body: AuthBody
    ) : Response<JsonObject>

    @Headers("${Constants.AUTHORIZATION}:Bearer ${Constants.API_KEY}")
    @PATCH(Constants.END_POINT_USER)
    suspend fun updateBalance(
        @Query("user_id") userId: String,
        @Query("apikey") apiKey: String,
        @Body balance: JsonObject
    ) : Response<Void>

    @Headers("${Constants.AUTHORIZATION}:Bearer ${Constants.API_KEY}")
    @PATCH(Constants.END_POINT_USER)
    suspend fun updateIncomeOutcome(
        @Query("user_id") userId: String,
        @Query("apikey") apiKey: String,
        @Body nominal: JsonObject
    ) : Response<Void>

    @Headers("${Constants.AUTHORIZATION}:Bearer ${Constants.API_KEY}")
    @POST(Constants.END_POINT_SIGNUP)
    suspend fun authenticationRegister(
        @Body body: AuthBody,
        @Query("apikey") apiKey: String,
    ): Response<JsonObject>

    @Headers("apikey:${Constants.API_KEY}")
    @POST(Constants.END_POINT_USER)
    suspend fun postBiodataUser(
        @Body body: BiodataUserModel,
    ): Response<Void>
}