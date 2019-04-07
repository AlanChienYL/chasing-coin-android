package mithril.hackathon.chasingcoin.ui.login

import android.net.Uri
import mithril.hackathon.chasingcoin.ui.base.BasePresenter
import timber.log.Timber

/**
 * Created by AlanChien on 07,April,2019.
 */
class LoginPresenter<V : LoginContract.View> : BasePresenter<V>(), LoginContract.Presenter {


    override fun create() {
        getView()?.setupWebView()
        val intentUri = Uri.parse("https://www.strava.com/oauth/mobile/authorize")
            .buildUpon()
            .appendQueryParameter("client_id", "33972")
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
            url.queryParameterNames.contains("code") -> {
                val code = url.getQueryParameter("code")!!
                dataInteractor?.prefsHelper?.code = code
                getView()?.loginSuccess()
                return true
            }
        }
        return false
    }


}