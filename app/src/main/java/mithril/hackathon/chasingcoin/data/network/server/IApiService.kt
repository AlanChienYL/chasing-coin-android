package mithril.hackathon.chasingcoin.data.network.server

import kotlinx.coroutines.Deferred
import mithril.hackathon.chasingcoin.data.network.server.request.LoginReq
import mithril.hackathon.chasingcoin.data.network.server.request.TokenExchangeReq
import mithril.hackathon.chasingcoin.data.network.server.request.TokenRefreshReq
import mithril.hackathon.chasingcoin.data.network.server.response.TokenExchangeResp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IApiService{
    @POST("oauth")
    fun login(@Body request: LoginReq): Deferred<Response<LoginReq>>

    @POST("exchange")
    fun tokenExchange(@Body request: TokenExchangeReq): Deferred<Response<TokenExchangeResp>>

    @POST("refresh")
    fun tokenRefresh(@Body request: TokenRefreshReq): Deferred<Response<TokenRefreshReq>>
}
