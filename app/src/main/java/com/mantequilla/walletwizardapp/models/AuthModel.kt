package com.mantequilla.walletwizardapp.models

data class AuthLoginModel (
    val access_token: String,
    val token_type: String,
    val expires_in: Long,
    val refresh_token: String,
    val user: User
)

data class User (
    val id: String,
    val aud: String,
    val role: String,
    val email: String,
    val email_confirmed_at: String,
    val phone: String,
    val confirmed_at: String,
    val last_sign_in_at: String,
    val app_metadata: AppMetadata,
    val user_metadata: UserMetadata,
    val identities: List<Identity>,
    val created_at: String,
    val updated_at: String
)

data class AppMetadata (
    val provider: String,
    val providers: List<String>
)

data class Identity (
    val id: String,
    val user_id: String,
    val identity_data: IdentityData,
    val provider: String,
    val last_sign_in_at: String,
    val created_at: String,
    val updated_at: String
)

data class IdentityData (
    val email: String,
    val sub: String
)

class UserMetadata()

data class AuthBody(
    val email: String,
    val password: String
)

