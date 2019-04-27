package mithril.hackathon.chasingcoin.data.network.server.response

import com.google.gson.annotations.SerializedName

data class NewsResp(
    @SerializedName("data") val data: Data?

) {

    data class Data(
        @SerializedName("count")
        val count: Any,
        @SerializedName("news")
        val news: ArrayList<News>
    )
    data class News(
        @SerializedName("winnerCount")
        val count: Any,
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
        val title:  String?,
        @SerializedName("marathonId")
        val marathonId:  Long?,
        @SerializedName("closed")
        val closed:  Boolean,
        @SerializedName("tag")
        val tag:  String?,
        @SerializedName("marathonReward")
        val reward:  Int
    )

}