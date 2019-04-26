package mithril.hackathon.chasingcoin.data.network.server

import kotlinx.coroutines.Deferred
import mithril.hackathon.chasingcoin.data.network.server.request.ChaserIdReq
import mithril.hackathon.chasingcoin.data.network.server.request.TokenRefreshReq
import mithril.hackathon.chasingcoin.data.network.server.response.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IApiService {

    @GET("/strava/exchange")
    fun tokenExchange(@Query("code") code: String): Deferred<Response<TokenExchangeResp>>

    @GET("/strava/refresh")
    fun tokenRefresh(@Query("code") code: String): Deferred<Response<TokenRefreshReq>>

    /**
     * 取得 profile's mith
     */
    @POST("chaser/mith")
    fun chaserMith(@Body request: ChaserIdReq): Deferred<Response<ChaserIdReq>>

    /**
     * 取得 profile's strava
     */
    @POST("chaser/strava")
    fun chaserStrava(@Body request: ChaserIdReq): Deferred<Response<ChaserIdReq>>

    /**
     * 取得 mith's authorize
     */
    @GET("mith_oauth/authorize")
    fun mithAuth(@Query("code") code: String): Deferred<Response<TokenRefreshReq>>

    /**
     * 取得 mith's token
     */
    @POST("mith_oauth/token")
    fun mithToken(@Body request: TokenRefreshReq): Deferred<Response<TokenRefreshReq>>

    /**
     * 取得 mith's unbind
     */
    @POST("mith_oauth/unbind")
    fun mithAuthUnbind(@Body request: TokenRefreshReq): Deferred<Response<TokenRefreshReq>>

    /**
     * 取得 mith's appBalance
     */
    @GET("/mith_oauth/balance")
    fun mithAuthAppBalance(@Query("code") code: String): Deferred<Response<TokenRefreshReq>>

    @POST("mith_mining")
    fun mithMining(@Body request: ChaserIdReq): Deferred<Response<MiningResp>>

    @POST("chaser/marathon/stats")
    fun getStats(@Body request: ChaserIdReq): Deferred<Response<StatsResp>>

    @GET("news")
    fun getNews(): Deferred<Response<GamesResp>>
    @POST("chaser/marathon/canjoin")
    fun checkJoinable(@Body request: ChaserIdReq): Deferred<Response<BaseResp>>

    @GET("mith_donate/app")
    fun donate(@Query("chaserId") chaserUid: String): Deferred<Response<BaseResp>>

}
