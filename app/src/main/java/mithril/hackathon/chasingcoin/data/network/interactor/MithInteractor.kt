package mithril.hackathon.chasingcoin.data.network.interactor

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mithril.hackathon.chasingcoin.data.DataInteractor
import mithril.hackathon.chasingcoin.data.network.server.request.ChaserIdReq
import mithril.hackathon.chasingcoin.data.network.server.response.BaseResp
import mithril.hackathon.chasingcoin.data.network.server.response.MithResp
import retrofit2.Response
import kotlin.reflect.KFunction1


class MithInteractor(
    di: DataInteractor,
    val successHandler: KFunction1<@ParameterName(name = "resp") MithResp, Unit>,
    val failureHandler: (BaseResp?) -> Unit
) : BaseInteractor(di) {


    fun getMithInfo(): Job = launch {
        try {
            val request = ChaserIdReq(di.prefsHelper.chaserUid!!)
            val resp = di.serverApiService.chaserMith(request).await()
            parser(resp)
        } catch (t: Throwable) {
            throwableStravaHandler(t, failureHandler)
        }
    }

    private fun parser(resp: Response<MithResp>) {
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