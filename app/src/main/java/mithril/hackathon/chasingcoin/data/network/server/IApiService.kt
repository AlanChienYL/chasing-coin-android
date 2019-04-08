package mithril.hackathon.chasingcoin.data.network.server

import kotlinx.coroutines.Deferred
import mithril.hackathon.chasingcoin.data.network.server.request.TokenExchangeReq
import mithril.hackathon.chasingcoin.data.network.server.response.TokenExchangeResp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by AlanChien on 07,April,2019.
 */

interface IApiService_authServer {
    @POST("exchange")
    fun tokenExchange(@Body request: TokenExchangeReq): Deferred<Response<TokenExchangeResp>>
}