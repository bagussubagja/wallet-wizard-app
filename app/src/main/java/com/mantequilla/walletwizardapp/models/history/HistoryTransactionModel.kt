package com.mantequilla.walletwizardapp.models.history

typealias HistoryTransactionModel = ArrayList<HistoryTransactionModelElement>

data class HistoryTransactionModelElement (
    val id: Long,
    val userID: String,
    val title: String,
    val isCredit: Boolean,
    val nominal: Long,
    val date: String,
    val currency: String,
)


