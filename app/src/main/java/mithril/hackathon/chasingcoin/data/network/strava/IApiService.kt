package mithril.hackathon.chasingcoin.data.network.strava

import kotlinx.coroutines.Deferred
import mithril.hackathon.chasingcoin.data.network.strava.request.TokenExchangeReq
import mithril.hackathon.chasingcoin.data.network.strava.response.TokenExchangeResp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import mithril.hackathon.chasingcoin.data.network.strava.response.GetRunningRacesResp
/**
 * Created by AlanChien on 07,April,2019.
 */
interface IApiService {
    @POST("oauth/token")
    fun tokenExchange(@Body request: TokenExchangeReq): Deferred<Response<TokenExchangeResp>>

    @GET("running_races")
    fun getRunningRaces(@Query("code") code: String): Deferred<Response<GetRunningRacesResp>>
}
