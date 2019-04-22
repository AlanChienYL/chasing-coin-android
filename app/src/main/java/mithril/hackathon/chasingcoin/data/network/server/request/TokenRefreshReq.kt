package mithril.hackathon.chasingcoin.data.network.server.request

import com.google.gson.annotations.SerializedName

data class TokenRefreshReq(
    @SerializedName("client_id") val clientId: String,
    @SerializedName("client_secret") val clientSecret: String,
    @SerializedName("code") val code: String,
    @SerializedName("grant_type") val authorizationCode: String = "authorization_code"
)