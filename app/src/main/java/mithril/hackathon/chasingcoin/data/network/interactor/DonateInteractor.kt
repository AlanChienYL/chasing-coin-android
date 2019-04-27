package mithril.hackathon.chasingcoin.data.network.interactor

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mithril.hackathon.chasingcoin.data.DataInteractor
import mithril.hackathon.chasingcoin.data.network.server.response.BaseResp
import retrofit2.Response

/**
 * Created by AlanChien on 19,April,2019.
 */
class DonateInteractor(
    di: DataInteractor,
    val successHandler: (BaseResp) -> Unit,
    val failureHandler: (BaseResp?) -> Unit
) : BaseInteractor(di) {


    fun request(): Job = launch {
        try {
            val resp = di.serverApiService.donate(di.prefsHelper.chaserUid!!).await()
            parser(resp)
        } catch (t: Throwable) {
            println("######################################")
            throwableHandler(t, failureHandler)
        }
    }

    private fun parser(resp: Response<BaseResp>) {
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