package mithril.hackathon.chasingcoin.data.network.server.response

import com.google.gson.annotations.SerializedName

data class MiningResp(
    @SerializedName("data")
    val mining: List<Mining>
) {
    data class Mining(
        @SerializedName("amount")
        val amount: Double,
        @SerializedName("happened_at")
        val happenedAt: String,
        @SerializedName("reward")
        val reward: Double,
        @SerializedName("status")
        val status: String,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("uuid")
        val uuid: String
    )
}