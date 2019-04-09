package mithril.hackathon.chasingcoin.data.network.strava.response

import com.google.gson.annotations.SerializedName

data class stravaBaseResp internal constructor(
    @SerializedName("error")
    var error: String? = null
)