package mithril.hackathon.chasingcoin.data.network.interactor

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mithril.hackathon.chasingcoin.data.DataInteractor
import mithril.hackathon.chasingcoin.data.network.strava.response.stravaBaseResp
import mithril.hackathon.chasingcoin.data.network.strava.response.GetRunningRacesResp
import retrofit2.Response

/**
 * Created by AlanChien on 20,March,2019.
 */

class RunningRacesInteractor(
    di: DataInteractor,
    val successHandler: (GetRunningRacesResp) -> Unit,
    val failureHandler: (stravaBaseResp?) -> Unit
) : BaseInteractor(di) {


    fun request(code: String): Job = launch {
        try {
            val resp = di.apiService.getRunningRaces(code).await()
            parser(resp)
        } catch (t: Throwable) {
            throwableStravaHandler(t, failureHandler)
        }
    }

    private fun parser(resp: Response<GetRunningRacesResp>) {
        when {
            resp.isSuccessful -> {
                resp.body()?.let { successHandler(it) }
                resp.body() ?: getStravaServerResponseFailed(failureHandler)
            }
            !resp.isSuccessful -> resp.errorBody()?.let {
                failureHandler(getStravaErrResp(it))
            }
        }
    }
}