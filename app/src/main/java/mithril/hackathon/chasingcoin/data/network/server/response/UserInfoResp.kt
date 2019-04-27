package mithril.hackathon.chasingcoin.data.network.server.response

import com.google.gson.annotations.SerializedName

data class UserInfoResp(
    @SerializedName("amount")
    val amount: String?,
    @SerializedName("balance")
    val balance: String,
    @SerializedName("kyc_level")
    val kycLevel: String,
    @SerializedName("stake_level")
    val stakeLevel: String,
    @SerializedName("staked_amount")
    val stakedAmount: String
)
