package mithril.hackathon.chasingcoin.data.network.strava

import kotlinx.coroutines.Deferred
import mithril.hackathon.chasingcoin.data.network.strava.request.TokenExchangeReq
import mithril.hackathon.chasingcoin.data.network.strava.response.TokenExchangeResp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by AlanChien on 07,April,2019.
 */
interface IApiService {
    @POST("oauth/token")
    fun tokenExchange(@Body request: TokenExchangeReq): Deferred<Response<TokenExchangeResp>>
}
