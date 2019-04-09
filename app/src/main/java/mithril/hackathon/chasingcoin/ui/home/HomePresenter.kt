package mithril.hackathon.chasingcoin.ui.home

import mithril.hackathon.chasingcoin.ui.base.BasePresenter

/**
 * Created by AlanChien on 09,April,2019.
 */
class HomePresenter<V : HomeContract.View> : BasePresenter<V>(), HomeContract.Presenter {

    override fun create() {
        getView()?.setAdapter()
    }
}