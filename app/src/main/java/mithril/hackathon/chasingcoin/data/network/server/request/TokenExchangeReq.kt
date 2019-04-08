package mithril.hackathon.chasingcoin.data.network.server.request

import com.google.gson.annotations.SerializedName

data class TokenExchangeReq(
    @SerializedName("code") val code: String
)