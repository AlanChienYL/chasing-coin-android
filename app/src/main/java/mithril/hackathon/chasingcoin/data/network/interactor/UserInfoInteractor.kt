package mithril.hackathon.chasingcoin.data.network.interactor

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mithril.hackathon.chasingcoin.data.DataInteractor
import mithril.hackathon.chasingcoin.data.network.server.request.MithTokenReq
import mithril.hackathon.chasingcoin.data.network.server.response.BaseResp
import mithril.hackathon.chasingcoin.data.network.server.response.UserInfoResp
import retrofit2.Response
import kotlin.reflect.KFunction1


class UserInfoInteractor(
    di: DataInteractor,
    val successHandler: (UserInfoResp) -> Unit,
    val failureHandler: KFunction1<@ParameterName(name = "resp") BaseResp?, Unit>
) : BaseInteractor(di) {


    fun getUserInfo(token:String): Job = launch {
        try {
            val request = MithTokenReq(token)
            val resp = di.serverApiService.mithUserInfo(request).await()
            parser(resp)
        } catch (t: Throwable) {
            throwableHandler(t, failureHandler)
        }
    }

    private fun parser(resp: Response<UserInfoResp>) {
        when {
            resp.isSuccessful -> {
                resp.body()?.let { successHandler(it) }
                resp.body()?: getServerResponseFailed(failureHandler)
            }
            !resp.isSuccessful -> resp.errorBody()?.let {
                failureHandler(getErrResp(it))
            }
        }
    }
}