package mithril.hackathon.chasingcoin.ui.home.main

import mithril.hackathon.chasingcoin.data.network.interactor.MithInteractor
import mithril.hackathon.chasingcoin.data.network.interactor.UserInfoInteractor
import mithril.hackathon.chasingcoin.data.network.server.response.BaseResp
import mithril.hackathon.chasingcoin.data.network.server.response.MithResp
import mithril.hackathon.chasingcoin.data.network.server.response.UserInfoResp
import mithril.hackathon.chasingcoin.ui.base.BasePresenter
import timber.log.Timber

/**
 * Created by AlanChien on 09,April,2019.
 */
class MemberPresenter<V : MemberContract.View> : BasePresenter<V>(), MemberContract.Presenter {
    override fun create() {

    }

    private val serverMithInter by lazy {
        MithInteractor(
            dataInteractor!!, ::apiMithSuccess, ::apiMithFailed
        )
    }

    private val userInfoInter by lazy {
        UserInfoInteractor(
            dataInteractor!!, ::apiUserInfoSuccess, ::apiUserInfoFailed
        )
    }

    private fun apiMithSuccess(resp: MithResp) {
        getView()?.hideProgress()

        println("---------------" + resp.mith!!.access_token + "---------------")

        userInfoInter.getUserInfo(resp.mith!!.access_token)
    }

    private fun apiMithFailed(resp: BaseResp?) {
        getView()?.hideProgress()
        Timber.e("in apiMithFailed ${resp?.error}. code : ${resp?.code}")
        resp?.error?.let { err ->
           println("MemberPresenter Error")
        }
    }

    private fun apiUserInfoSuccess(resp: UserInfoResp) {
        getView()?.hideProgress()

        getView()!!.setUserMithBalance(resp.amount)
        getView()!!.setUserMithLevel(resp.stakeLevel)
        getView()!!.setUserMithTotal(resp.balance)
        getView()!!.setUserMithStakedAmount(resp.stakedAmount)

    }
    private fun apiUserInfoFailed(resp: BaseResp?) {
        getView()?.hideProgress()
        Timber.e("in apiUserInfoFailed ${resp?.error}. code : ${resp?.code}")
        resp?.error?.let { err ->
            println("MemberPresenter Error")
        }
    }

    override fun onViewCreated() {
        getView()?.showProgress()
        serverMithInter.getMithInfo()

    }
}