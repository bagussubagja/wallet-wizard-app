package com.mantequilla.walletwizardapp.models

data class BiodataUserModel(
    val user_id: String,
    val email: String,
    val name: String,
    val currency: String,
    val balance: Int,
    val money_spent: Int,
    val income: Int
)
