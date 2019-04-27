package mithril.hackathon.chasingcoin.data.network.interactor

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mithril.hackathon.chasingcoin.data.DataInteractor
import mithril.hackathon.chasingcoin.data.network.server.request.ChaserIdReq
import mithril.hackathon.chasingcoin.data.network.server.response.BaseResp
import mithril.hackathon.chasingcoin.data.network.server.response.CanJoinResp
import retrofit2.Response
import kotlin.reflect.KFunction1

/**
 * Created by AlanChien on 19,April,2019.
 */
class CheckJoinInteractor(
    di: DataInteractor,
    val successHandler: KFunction1<@ParameterName(name = "resp") CanJoinResp, Unit>,
    val failureHandler: (BaseResp?) -> Unit
) : BaseInteractor(di) {


    fun request(): Job = launch {
        try {

            val request = ChaserIdReq(di.prefsHelper.chaserUid!!)
            val resp = di.serverApiService.checkJoinable(request).await()
            parser(resp)
        } catch (t: Throwable) {
            throwableHandler(t, failureHandler)
        }
    }

    private fun parser(resp: Response<CanJoinResp>) {
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