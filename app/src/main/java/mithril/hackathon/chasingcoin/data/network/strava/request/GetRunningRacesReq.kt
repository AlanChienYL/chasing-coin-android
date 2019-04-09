package mithril.hackathon.chasingcoin.data.network.strava.request

import com.google.gson.annotations.SerializedName
import java.util.*

data class GetRunningRacesReq(
    @SerializedName("country") val country: String,
    @SerializedName("running_race_type") val runningRaceType: Int,
    @SerializedName("route_ids") val routeIds: IntArray,
    @SerializedName("distance") val distance: Float,
    @SerializedName("city") val city: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("state") val state: String,
    @SerializedName("url") val url: String,
    @SerializedName("start_date_local") val date: Date
)

