package mithril.hackathon.chasingcoin.data.network.server.response

import com.google.gson.annotations.SerializedName

data class TokenExchangeResp(
    @SerializedName("chasingToken") val chasingToken: String?,
    @SerializedName("chasingUser") val chasingUser: ChasingUser
) {

    data class ChasingUser(
        @SerializedName("uid")
        val uid: String,
        @SerializedName("displayName")
        val displayName: String,
        @SerializedName("photoURL")
        val photoURL: String,
        @SerializedName("sex")
        val sex: String,
        @SerializedName("strava")
        val strava: Strava
    )

    data class Strava(
        @SerializedName("uid") val uid: Long,
        @SerializedName("access_token") val accessToken: String?,
        @SerializedName("refresh_token") val refreshToken: String?,
        @SerializedName("expires_at") val expireAt: Long?,
        @SerializedName("expires_in") val expireIn: Int?
    )
}