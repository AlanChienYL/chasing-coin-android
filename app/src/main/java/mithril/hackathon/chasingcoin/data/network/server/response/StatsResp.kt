package mithril.hackathon.chasingcoin.data.network.server.response

import com.google.gson.annotations.SerializedName

data class StatsResp(
    @SerializedName("marathonId")
    val marathonId: String,
    @SerializedName("my")
    val my: My,
    @SerializedName("start")
    val start: Long,
    @SerializedName("stats")
    val stats: Stats
) {

    data class Stats(
        @SerializedName("amount")
        val amount: Int,
        @SerializedName("countOfRunners")
        val countOfRunners: Int,
        @SerializedName("marathonId")
        val marathonId: String,
        @SerializedName("totalDistance")
        val totalDistance: Double,
        @SerializedName("countdownDay")
        val countdownDay: Double
    )

    data class My(
        @SerializedName("amount")
        val amount: Long,
        @SerializedName("rank")
        val rank: String,
        @SerializedName("totalDistance")
        val totalDistance: Double
    )


}