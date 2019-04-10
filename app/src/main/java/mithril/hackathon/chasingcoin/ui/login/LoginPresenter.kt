package mithril.hackathon.chasingcoin.ui.login

import android.net.Uri
import mithril.hackathon.chasingcoin.BuildConfig
import mithril.hackathon.chasingcoin.data.network.interactor.TokenExchangeInteractor
import mithril.hackathon.chasingcoin.data.network.server.response.BaseResp
import mithril.hackathon.chasingcoin.data.network.server.response.TokenExchangeResp
import mithril.hackathon.chasingcoin.ui.base.BasePresenter
import timber.log.Timber

/**
 * Created by AlanChien on 07,April,2019.
 */
class LoginPresenter<V : LoginContract.View> : BasePresenter<V>(), LoginContract.Presenter {
    private val tokenInter by lazy {
        TokenExchangeInteractor(dataInteractor!!, ::apiSuccess, ::apiFailed)
    }


    override fun create() {
        getView()?.setupWebView()
        val intentUri = Uri.parse("https://www.strava.com/oauth/mobile/authorize")
            .buildUpon()
            .appendQueryParameter("client_id", BuildConfig.CLIENT_ID)
            .appendQueryParameter("redirect_uri", "https://127.0.0.1/chasing/")
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("approval_prompt", "auto")
            .appendQueryParameter("scope", "profile:read_all,activity:read")
            .build()
        Timber.d("auth url : $intentUri")
        getView()?.loadLoginUrl(intentUri.toString())
    }

    override fun pageStart() {
        getView()?.showProgress()
    }

    override fun pageFinish() {
        getView()?.hideProgress()
    }

    override fun shouldOverrideUrlLoading(url: Uri): Boolean {
        Timber.d("in shouldOverrideUrlLoading: $url")
        when {
            url.host == "127.0.0.1" && url.queryParameterNames.contains("code") -> {
                val code = url.getQueryParameter("code")!!
                tokenInter.request(code)
                getView()?.showProgress()
                return true
            }
        }
        return false
    }


    private fun apiSuccess(resp: TokenExchangeResp) {
        getView()?.hideProgress()
        dataInteractor?.prefsHelper?.chasingToken = resp.chasingToken
        dataInteractor?.prefsHelper?.stravaToken = resp.chasingUser.strava.accessToken
        dataInteractor?.prefsHelper?.uid = resp.chasingUser.strava.uid

        getView()?.loginSuccess()
        Timber.d("access token : ${resp.chasingToken}, uid ${resp.chasingUser.strava.uid}")
    }

    private fun apiFailed(resp: BaseResp?) {
        getView()?.hideProgress()
    }
}