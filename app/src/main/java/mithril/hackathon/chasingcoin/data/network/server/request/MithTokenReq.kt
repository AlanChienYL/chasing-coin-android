package mithril.hackathon.chasingcoin.data.network.server.request

import com.google.gson.annotations.SerializedName

data class MithTokenReq(
    @SerializedName("mith_access_token") val MithAccessToken: String
)