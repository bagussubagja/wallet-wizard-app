package com.mantequilla.walletwizardapp.models

data class UserModel(
    val id : Int?,
    val user_id : String?,
    val email : String?,
    val name : String?,
    val currency : String?,
    val balance : Long?,
    val money_spent : Long?,
    val income: Long?,
)
