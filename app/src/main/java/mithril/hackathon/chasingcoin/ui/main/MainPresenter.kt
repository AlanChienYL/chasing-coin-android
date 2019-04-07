package mithril.hackathon.chasingcoin.ui.main

import mithril.hackathon.chasingcoin.ui.base.BasePresenter

/**
 * Created by AlanChien on 07,April,2019.
 */
class MainPresenter<V : MainContract.View> : BasePresenter<V>(), MainContract.Presenter {


    override fun create() {
        val code = dataInteractor?.prefsHelper?.code
        when (code.isNullOrEmpty()) {
            true -> getView()?.navigateToLogin()
            false -> getView()?.setCode(code)
        }
    }

    override fun loginSuccess() {
        dataInteractor?.prefsHelper?.code?.let { code ->
            getView()?.setCode(code)
        }
    }
}