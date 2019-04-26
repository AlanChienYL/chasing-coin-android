package mithril.hackathon.chasingcoin.data.network.server.response

import com.google.gson.annotations.SerializedName

data class GamesResp(
    @SerializedName("data") val data: Data?
) {

    data class Data(
        @SerializedName("count")
        val count: Int,
        @SerializedName("news")
        val news: MutableList<News>
    )

    data class News(
        @SerializedName("winnerCount")
        val count: Int,
        @SerializedName("winnerDistance")
        val winnerDistance: Any,
        @SerializedName("totalDonation")
        val totalDonation: Any,
        @SerializedName("closeAt")
        val closeAt: Long,
        @SerializedName("startAt")
        val startAt: Long,
        @SerializedName("publishAt")
        val publishAt: Long,
        @SerializedName("title")
        val title: String?,
        @SerializedName("marathonId")
        val marathonId: Long?,
        @SerializedName("closed")
        val closed: Boolean,
        @SerializedName("tag")
        val tag: String?,
        @SerializedName("marathonReward")
        val reward: Int
    )

}