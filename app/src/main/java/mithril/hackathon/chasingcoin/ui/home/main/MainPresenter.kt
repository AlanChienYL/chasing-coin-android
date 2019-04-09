package mithril.hackathon.chasingcoin.ui.home.main

import mithril.hackathon.chasingcoin.data.network.interactor.AthleteStatsInteractor
import mithril.hackathon.chasingcoin.data.network.server.response.BaseResp
import mithril.hackathon.chasingcoin.data.network.strava.response.AthleteStatsResp
import mithril.hackathon.chasingcoin.ui.base.BasePresenter

/**
 * Created by AlanChien on 09,April,2019.
 */
class MainPresenter<V : MainContract.View> : BasePresenter<V>(), MainContract.Presenter {

    private val athleteStatsInter by lazy {
        AthleteStatsInteractor(
                dataInteractor!!, ::apiAthleteStatsSuccess, ::apiAthleteStatsFailed
        )
    }

    override fun create() {
        val uid = dataInteractor!!.prefsHelper.uid
        athleteStatsInter.getStats(uid)
    }

    private fun apiAthleteStatsSuccess(resp: AthleteStatsResp) {

    }

    private fun apiAthleteStatsFailed(resp: BaseResp?) {
        resp?.error?.let { err ->
            getView()?.showError(err)
        }
    }

}