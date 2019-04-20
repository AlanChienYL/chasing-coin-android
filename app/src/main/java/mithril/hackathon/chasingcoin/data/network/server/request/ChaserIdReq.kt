package mithril.hackathon.chasingcoin.data.network.server.request

import com.google.gson.annotations.SerializedName

//TODO
data class ChaserIdReq(
    @SerializedName("chaserId") val clientId: String
)