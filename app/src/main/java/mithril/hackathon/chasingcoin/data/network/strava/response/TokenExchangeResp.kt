package mithril.hackathon.chasingcoin.data.network.strava.response

import com.google.gson.annotations.SerializedName

data class TokenExchangeResp(
    @SerializedName("error") val error: String?,
    @SerializedName("access_token") val accessToken: String?,
    @SerializedName("refresh_token") val refreshToken: String?,
    @SerializedName("expires_at") val expireAt: Long?,
    @SerializedName("expires_in") val expireIn: Int?,
    @SerializedName("token_type") val tokenType: String?,
    @SerializedName("athlete") val athlete: Athlete?

) {

    data class Athlete(
        @SerializedName("badge_type_id")
        val badgeTypeId: Int,
        @SerializedName("city")
        val city: String?,
        @SerializedName("country")
        val country: String,
        @SerializedName("created_at")
        val created_at: String,
        @SerializedName("firstname")
        val firstName: String,
        @SerializedName("follower")
        val follower: Any,
        @SerializedName("friend")
        val friend: Any,
        @SerializedName("id")
        val id: Int,
        @SerializedName("lastname")
        val lastName: String,
        @SerializedName("premium")
        val premium: Boolean,
        @SerializedName("profile")
        val profile: String,
        @SerializedName("profile_medium")
        val profileMedium: String,
        @SerializedName("resource_state")
        val resourceState: Int,
        @SerializedName("sex")
        val sex: String?,
        @SerializedName("state")
        val state: String,
        @SerializedName("summit")
        val summit: Boolean,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("username")
        val username: String?
    )
}