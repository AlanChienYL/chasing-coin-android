package mithril.hackathon.chasingcoin.data.network.strava.response

import com.google.gson.annotations.SerializedName

data class AthleteStatsResp(
    @SerializedName("all_run_totals") val allRunTotals: AllRunTotals,
    @SerializedName("biggest_climb_elevation_gain") val biggestClimbElevationGain: Any,
    @SerializedName("recent_run_totals") val recentRunTotals: RecentRunTotals,
    @SerializedName("ytd_run_totals") val ytdRunTotals: YtdRunTotals
) {

    data class AllRunTotals(
        @SerializedName("count")
        val count: Int,
        @SerializedName("distance")
        val distance: Int,
        @SerializedName("elapsed_time")
        val elapsedTime: Int,
        @SerializedName("elevation_gain")
        val elevationGain: Int,
        @SerializedName("moving_time")
        val moving_time: Int
    )

    data class RecentRunTotals(
        @SerializedName("achievement_count")
        val achievement_count: Int,
        @SerializedName("count")
        val count: Int,
        @SerializedName("distance")
        val distance: Double,
        @SerializedName("elapsed_time")
        val elapsed_time: Int,
        @SerializedName("elevation_gain")
        val elevation_gain: Double,
        @SerializedName("moving_time")
        val moving_time: Int
    )

    data class YtdRunTotals(
        @SerializedName("count")
        val count: Int,
        @SerializedName("distance")
        val distance: Double,
        @SerializedName("elapsed_time")
        val elapsed_time: Int,
        @SerializedName("elevation_gain")
        val elevation_gain: Int,
        @SerializedName("moving_time")
        val moving_time: Int
    )

}