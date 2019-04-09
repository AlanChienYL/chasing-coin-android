package mithril.hackathon.chasingcoin.ui.main

import mithril.hackathon.chasingcoin.ui.base.BasePresenter

/**
 * Created by AlanChien on 07,April,2019.
 */
class MainPresenter<V : MainContract.View> : BasePresenter<V>(), MainContract.Presenter {


    override fun create() {
        val token = dataInteractor?.prefsHelper?.token
        when (token.isNullOrEmpty()) {
            false -> getView()?.navigateToHome()
        }
    }

    override fun loginSuccess() {
        dataInteractor?.prefsHelper?.token?.let { token ->
            getView()?.setToken(token)
        }
    }

    override fun clickToLogin() {
        getView()?.navigateToLogin()
    }
}