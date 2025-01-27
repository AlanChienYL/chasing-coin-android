package mithril.hackathon.chasingcoin.data.network.strava

import kotlinx.coroutines.Deferred
import mithril.hackathon.chasingcoin.data.network.strava.request.TokenExchangeReq
import mithril.hackathon.chasingcoin.data.network.strava.response.AthleteStatsResp
import mithril.hackathon.chasingcoin.data.network.strava.response.TokenExchangeResp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by AlanChien on 07,April,2019.
 */
interface IApiService {

    @GET("api/v3/athletes/{id}/stats")
    fun aithetesStats(@Path("id") uid: Long): Deferred<Response<AthleteStatsResp>>

    @POST("oauth/token")
    fun tokenRefresh(@Body request: TokenExchangeReq): Deferred<Response<TokenExchangeResp>>
}
