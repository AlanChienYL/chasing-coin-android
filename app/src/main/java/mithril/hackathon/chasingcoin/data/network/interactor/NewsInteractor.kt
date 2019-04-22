package mithril.hackathon.chasingcoin.data.network.interactor

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mithril.hackathon.chasingcoin.data.DataInteractor
import mithril.hackathon.chasingcoin.data.network.server.response.BaseResp
import mithril.hackathon.chasingcoin.data.network.server.response.GamesResp
import retrofit2.Response


class NewsInteractor(
    di: DataInteractor,
    val successHandler: (GamesResp) -> Unit,
    val failureHandler: (BaseResp?) -> Unit
) : BaseInteractor(di) {


    fun getNews(): Job = launch {
        try {
            val resp = di.serverApiService.getNews().await()
            parser(resp)
        } catch (t: Throwable) {
            throwableStravaHandler(t, failureHandler)
        }
    }

    private fun parser(resp: Response<GamesResp>) {
        when {
            resp.isSuccessful -> {
                resp.body()?.let { successHandler(it) }
                resp.body() ?: getStravaServerResponseFailed(failureHandler)
            }
            !resp.isSuccessful -> resp.errorBody()?.let {
                failureHandler(getErrResp(it))
            }
        }
    }
}