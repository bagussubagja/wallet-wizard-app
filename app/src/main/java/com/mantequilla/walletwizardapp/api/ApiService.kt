package com.mantequilla.walletwizardapp.api

import com.mantequilla.walletwizardapp.helper.Constants
import com.mantequilla.walletwizardapp.models.history.HistoryTransactionModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.END_POINT_HISTORY_TRANSACTION)
    suspend fun getHistoryTransaction() : Response<HistoryTransactionModel>
}