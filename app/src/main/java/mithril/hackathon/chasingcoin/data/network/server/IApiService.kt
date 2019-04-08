package mithril.hackathon.chasingcoin.data.network.server

import kotlinx.coroutines.Deferred
import mithril.hackathon.chasingcoin.data.network.server.request.TokenRefreshReq
import mithril.hackathon.chasingcoin.data.network.server.response.TokenExchangeResp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IApiService {

    @GET("exchange")
    fun tokenExchange(@Query("code") code: String): Deferred<Response<TokenExchangeResp>>

    @POST("refresh")
    fun tokenRefresh(@Body request: TokenRefreshReq): Deferred<Response<TokenRefreshReq>>
}
