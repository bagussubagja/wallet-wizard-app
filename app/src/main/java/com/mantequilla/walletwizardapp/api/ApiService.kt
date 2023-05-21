package com.mantequilla.walletwizardapp.api

import com.google.gson.JsonObject
import com.mantequilla.walletwizardapp.helper.Constants

import com.mantequilla.walletwizardapp.models.HistoryTransactionModelElement
import com.mantequilla.walletwizardapp.models.UserModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
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
        @Body body : JsonObject,
        @Query("apikey") apikey: String = Constants.API_KEY
    ) : Response<Void>
}