package mithril.hackathon.chasingcoin.data.network.interactor

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mithril.hackathon.chasingcoin.BuildConfig
import mithril.hackathon.chasingcoin.data.DataInteractor
import mithril.hackathon.chasingcoin.data.network.server.response.BaseResp

import mithril.hackathon.chasingcoin.data.network.strava.request.TokenExchangeReq
import mithril.hackathon.chasingcoin.data.network.strava.response.TokenExchangeResp
import retrofit2.Response

/**
 * Created by AlanChien on 20,March,2019.
 */

class TokenRefreshInteractor(
    di: DataInteractor,
    val successHandler: (TokenExchangeResp) -> Unit,
    val failureHandler: (BaseResp?) -> Unit
) : BaseInteractor(di) {


    fun request(): Job = launch {
        try {
            val request = TokenExchangeReq(
                BuildConfig.CLIENT_ID,
                BuildConfig.CLIENT_SECRET,
                di.prefsHelper.refreshToken!!
            )
            val resp = di.apiService.tokenRefresh(request).await()
            parser(resp)
        } catch (t: Throwable) {
            throwableHandler(t, failureHandler)
        }
    }

    private fun parser(resp: Response<TokenExchangeResp>) {
        when {
            resp.isSuccessful -> {
                resp.body()?.let { successHandler(it) }
                resp.body() ?: getServerResponseFailed(failureHandler)
            }
            !resp.isSuccessful -> resp.errorBody()?.let {
                failureHandler(getErrResp(it))
            }
        }
    }
}