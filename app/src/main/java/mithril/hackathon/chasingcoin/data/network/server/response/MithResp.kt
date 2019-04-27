package mithril.hackathon.chasingcoin.data.network.server.response

import com.google.gson.annotations.SerializedName

data class MithResp(
    @SerializedName("mith") val mith: Mith?
) {

    data class Mith(
        @SerializedName("access_token")
        val access_token: String
    )
}