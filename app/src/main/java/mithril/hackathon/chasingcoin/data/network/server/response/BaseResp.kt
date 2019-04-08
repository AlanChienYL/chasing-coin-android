package mithril.hackathon.chasingcoin.data.network.server.response

import com.google.gson.annotations.SerializedName

data class BaseResp internal constructor(
    @SerializedName("error")
    var error: String? = null
)