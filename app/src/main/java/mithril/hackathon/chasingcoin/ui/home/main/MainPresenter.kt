package mithril.hackathon.chasingcoin.ui.home.main

import mithril.hackathon.chasingcoin.data.network.interactor.*
import mithril.hackathon.chasingcoin.data.network.server.response.BaseResp
import mithril.hackathon.chasingcoin.data.network.server.response.CanJoinResp
import mithril.hackathon.chasingcoin.data.network.server.response.StatsResp
import mithril.hackathon.chasingcoin.data.network.strava.response.AthleteStatsResp
import mithril.hackathon.chasingcoin.data.network.strava.response.TokenExchangeResp
import mithril.hackathon.chasingcoin.ui.base.BasePresenter
import mithril.hackathon.chasingcoin.utils.format
import timber.log.Timber

/**
 * Created by AlanChien on 09,April,2019.
 */
class MainPresenter<V : MainContract.View> : BasePresenter<V>(), MainContract.Presenter {

    private val athleteStatsInter by lazy {
        AthleteStatsInteractor(
            dataInteractor!!, ::apiAthleteStatsSuccess, ::apiAthleteStatsFailed
        )
    }

    private val refreshInter by lazy {
        TokenRefreshInteractor(
            dataInteractor!!, ::apiRefreshSuccess, ::apiRefreshFailure
        )
    }

    private val serverStatsInter by lazy {
        ServerStatsInteractor(
            dataInteractor!!, ::apiStatsSuccess, ::apiStatsFailed
        )
    }

    private val checkJoinInter by lazy {
        CheckJoinInteractor(
            dataInteractor!!, ::apiCanJoinSuccess, ::apiCanJoinFailed
        )
    }

    private val donateInter by lazy {
        DonateInteractor(
            dataInteractor!!, {}, ::apiAthleteStatsFailed
        )
    }

    override fun create() {

    }

    private fun apiAthleteStatsSuccess(resp: AthleteStatsResp) {
        getView()?.hideProgress()
        val yearDis = resp.ytdRunTotals.distance / 1000.0
        val weekDis = resp.recentRunTotals.distance / 1000.0
        getView()?.setYearKm(yearDis.format())
        getView()?.set4WeekKm(weekDis.format())
    }

    private fun apiAthleteStatsFailed(resp: BaseResp?) {
        getView()?.hideProgress()
        Timber.e("in apiAthleteStatsFailed ${resp?.error}. code : ${resp?.code}")
        resp?.error?.let { err ->
            getView()?.showError(err)
        }
        when (resp?.code == 401) {
            true -> refreshInter.request()
            else -> getView()?.hideProgress()
        }
    }

    private fun apiRefreshSuccess(resp: TokenExchangeResp) {
        dataInteractor?.prefsHelper?.refreshToken = resp.refreshToken
        dataInteractor?.prefsHelper?.stravaToken = resp.accessToken
        val uid = dataInteractor!!.prefsHelper.stravaUid
        athleteStatsInter.getStats(uid)
    }

    private fun apiRefreshFailure(resp: BaseResp?) {
        getView()?.hideProgress()
        Timber.e("in apiRefreshFailure ${resp?.error}")
    }

    private fun apiStatsFailed(resp: BaseResp?) {
        getView()?.hideProgress()
        Timber.e("in apiStatsFailed ${resp?.error}. code : ${resp?.code}")
        resp?.error?.let { err ->
            getView()?.showError(err)
        }
        when (resp?.code == 401) {
            true -> refreshInter.request()
            else -> getView()?.hideProgress()
        }
    }

    private fun apiStatsSuccess(resp: StatsResp) {
        getView()?.hideProgress()
        getView()?.setTotalKMNow(resp.stats.totalDistance.format())
        getView()?.setRankingPer(resp.my.rank)
        getView()?.setRemainDays(String.format("%.0f", resp.stats.countdownDay))

        getView()?.setRoundDistance(resp.stats.totalDistance)
        getView()?.setRoundJoinCheck(resp.my.totalDistance)
        getView()?.setRoundLastDay(resp.stats.countdownDay)
        getView()?.setRoundRank(resp.my.rank,resp.stats.countOfRunners.toString())
        getView()?.setRoundPrize(resp.stats.amount.toString())
    }

    private fun apiCanJoinSuccess(resp: CanJoinResp) {
        getView()?.hideProgress()

        if (resp.answer!! == false){
            //TODO show join donate button , button must add listener,
            //      if click button, redirect url to donate address
            println("---------------- show button ----------------")
        }
    }
    private fun apiCanJoinFailed(resp: BaseResp?) {
        getView()?.hideProgress()
        Timber.e("in apiCanJoin ${resp?.error}. code : ${resp?.code}")
        resp?.error?.let { err ->
            getView()?.showError(err)
        }
    }

    override fun onViewCreated() {
        getView()?.showProgress()
        val uid = dataInteractor!!.prefsHelper.stravaUid
        athleteStatsInter.getStats(uid)
        serverStatsInter.getStats()
        checkJoinInter.request()
//        donateInter.request()
    }
}