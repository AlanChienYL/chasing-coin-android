package mithril.hackathon.chasingcoin.ui.main

import mithril.hackathon.chasingcoin.data.network.interactor.MithMiningInteractor
import mithril.hackathon.chasingcoin.ui.base.BasePresenter
import timber.log.Timber

/**
 * Created by AlanChien on 07,April,2019.
 */
class MainPresenter<V : MainContract.View> : BasePresenter<V>(), MainContract.Presenter {
    private val mithMining by lazy {
        MithMiningInteractor(dataInteractor!!, {
            getView()?.navigateToHome()
        }, {
            Timber.e("mith error!!!!!!!!")
        })
    }

    override fun create() {
        val token = dataInteractor?.prefsHelper?.chasingToken
        when (token.isNullOrEmpty()) {
            false -> {
                mithMining.request()
            }
        }
    }

    override fun clickToLogin() {
        getView()?.navigateToLogin()
    }
}