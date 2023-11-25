package com.mantequilla.walletwizardapp.models


data class HistoryTransactionModelElement (
    val id : Int?,
    val user_id: String?,
    val title: String?,
    val is_credit: Boolean?,
    val nominal: Int?,
    val date: String?,
    val currency: String?,
)


