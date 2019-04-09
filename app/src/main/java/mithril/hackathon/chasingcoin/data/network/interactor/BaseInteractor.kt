package mithril.hackathon.chasingcoin.data.network.interactor

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.gson.Gson
import kotlinx.coroutines.*
import mithril.hackathon.chasingcoin.data.DataInteractor
import mithril.hackathon.chasingcoin.data.network.server.response.BaseResp
import mithril.hackathon.chasingcoin.data.network.strava.response.stravaBaseResp
import okhttp3.ResponseBody
import timber.log.Timber
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext

/**
 * Created by AlanChien on 16,January,2019.
 */
abstract class BaseInteractor(val di: DataInteractor) : LifecycleObserver, CoroutineScope {

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stop() {
        coroutineContext.cancelChildren()
    }

    fun <T> getErrParser(errorBody: String, classOfT: Class<T>): T {
        return Gson().fromJson(errorBody, classOfT)
    }

    fun getErrResp(errBody: ResponseBody) = let {
        try {
            getErrParser(errBody.string(), BaseResp::class.java)
        } catch (e: Throwable) {
            BaseResp(e.localizedMessage)
        }
    }

    fun getServerResponseFailed(failureHandler: (BaseResp?) -> Unit) {
        failureHandler(BaseResp("no resp from server"))
    }

    fun throwableHandler(t: Throwable, failureHandler: (BaseResp?) -> Unit): Job = launch {
        Timber.d("in throwableHandler err : ${t.message}")
        val baseResp =
            when (t) {
                is UnknownHostException -> BaseResp(t.localizedMessage)
                else -> BaseResp(t.localizedMessage)
            }
        failureHandler(baseResp)
    }

    fun getStravaErrResp(errBody: ResponseBody) = let {
        try {
            getErrParser(errBody.string(), stravaBaseResp::class.java)
        } catch (e: Throwable) {
            stravaBaseResp(e.localizedMessage)
        }
    }

    fun getStravaServerResponseFailed(failureHandler: (stravaBaseResp?) -> Unit) {
        failureHandler(stravaBaseResp("no strava resp from server"))
    }

    fun throwableStravaHandler(t: Throwable, failureHandler: (stravaBaseResp?) -> Unit): Job = launch {
        Timber.d("in strava throwableHandler err : ${t.message}")
        val stravaBaseResp =
            when (t) {
                is UnknownHostException -> stravaBaseResp(t.localizedMessage)
                else -> stravaBaseResp(t.localizedMessage)
            }
        failureHandler(stravaBaseResp)
    }


}