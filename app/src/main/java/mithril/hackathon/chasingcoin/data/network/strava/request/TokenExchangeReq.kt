package mithril.hackathon.chasingcoin.data.network.strava.request

import com.google.gson.annotations.SerializedName

/**
 * Created by AlanChien on 07,April,2019.
 */
data class TokenExchangeReq(
    @SerializedName("client_id") val clientId: String,
    @SerializedName("client_secret") val clientSecret: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("grant_type") val authorizationCode: String = "refresh_token"
)