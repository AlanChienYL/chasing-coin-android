package mithril.hackathon.chasingcoin.data.network.strava.response

import com.google.gson.annotations.SerializedName

data class AthleteStatsResp(
    @SerializedName("recent_run_totals") val recentRunTotals: Int?,
    @SerializedName("all_run_totals") val allRunTotals: Int?,
    @SerializedName("recent_swim_totals") val recentSwimTotals: Int?,
    @SerializedName("biggest_ride_distance") val biggestRideDistance: Float,
    @SerializedName("ytd_swim_totals") val ytdSwimTotals: Int?,
    @SerializedName("all_swim_totals") val allSwimTotals: Int?,
    @SerializedName("recent_ride_totals") val recentRideTotals: RecentRideTotals,
    @SerializedName("biggest_climb_elevation_gain") val biggestClimbElevationGain: Float,
    @SerializedName("ytd_ride_totals") val ytdRideTotals: Int?,
    @SerializedName("all_ride_totals") val allRideTotals:Int?,
    @SerializedName("ytd_run_totals") val ytdRunTotals:Int?
){
    data class RecentRideTotals(
        @SerializedName("distance")
        val distance: Float?,
        @SerializedName("achievement_count")
        val achievementCount: Int,
        @SerializedName("count")
        val count: Int,
        @SerializedName("elapsed_time")
        val elapsedTime: Int,
        @SerializedName("elevation_gain")
        val elevationGain: Float?,
        @SerializedName("moving_time")
        val movingTime: Int
    )
}

