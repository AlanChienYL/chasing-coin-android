package mithril.hackathon.chasingcoin.data.network.interactor

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mithril.hackathon.chasingcoin.data.DataInteractor
import mithril.hackathon.chasingcoin.data.network.server.response.BaseResp
import mithril.hackathon.chasingcoin.data.network.strava.response.AthleteStatsResp
import retrofit2.Response

/**
 * Created by AlanChien on 20,March,2019.
 */

class AthleteStatsInteractor(
        di: DataInteractor,
        val successHandler: (AthleteStatsResp) -> Unit,
        val failureHandler: (BaseResp?) -> Unit
) : BaseInteractor(di) {


    fun getStats(uid: Long): Job = launch {
        try {
            val resp = di.apiService.aithetesStats(uid).await()
            parser(resp)
        } catch (t: Throwable) {
            throwableStravaHandler(t, failureHandler)
        }
    }

    private fun parser(resp: Response<AthleteStatsResp>) {
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