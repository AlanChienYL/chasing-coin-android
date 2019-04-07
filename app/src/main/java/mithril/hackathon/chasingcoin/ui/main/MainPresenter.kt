package mithril.hackathon.chasingcoin.ui.main

import android.net.Uri
import mithril.hackathon.chasingcoin.ui.base.BasePresenter
import timber.log.Timber

/**
 * Created by AlanChien on 07,April,2019.
 */
class MainPresenter<V : MainContract.View> : BasePresenter<V>(), MainContract.Presenter {

    override fun create() {
        val acc = dataInteractor?.prefsHelper?.account
        takeIf { acc.isNullOrEmpty() }?.let {
            val intentUri = Uri.parse("https://www.strava.com/oauth/authorize")
                .buildUpon()
                .appendQueryParameter("client_id", "33972")
                .appendQueryParameter("redirect_uri", "http://chasing-coin.firebaseapp.com/exchange_token")
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("scope", "profile:read_all,activity:read")
                .build()
            Timber.d("auth url : $intentUri")
            getView()?.startStravaLogin(intentUri)
        }

    }

}