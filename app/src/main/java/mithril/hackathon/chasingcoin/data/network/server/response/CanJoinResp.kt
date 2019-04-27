package mithril.hackathon.chasingcoin.data.network.server.response

import com.google.gson.annotations.SerializedName

data class CanJoinResp(
    @SerializedName("answer") val answer:Any?
)