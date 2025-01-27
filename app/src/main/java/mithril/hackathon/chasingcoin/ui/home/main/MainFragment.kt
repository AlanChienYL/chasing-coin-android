package mithril.hackathon.chasingcoin.ui.home.main

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_main.*
import mithril.hackathon.chasingcoin.R
import mithril.hackathon.chasingcoin.di.Injection
import mithril.hackathon.chasingcoin.ui.base.BaseFragment
import mithril.hackathon.chasingcoin.utils.Constants
import mithril.hackathon.chasingcoin.utils.getDateMinOnly
import org.jetbrains.anko.design.longSnackbar
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by AlanChien on 09,April,2019.
 */
class MainFragment : BaseFragment(), MainContract.View {

    val presenter: MainPresenter<MainContract.View> by lazy { MainPresenter<MainContract.View>() }
    override fun initializePresenter() = with(presenter) {
        setView(this@MainFragment)
        lifecycle = this@MainFragment.lifecycle
        dataInteractor = Injection.provideDataInteractor(
            Injection.providePrefsHelper(
                activity!!, Constants.SharePreferences.SPFS_NAME
            )
        )
        initialize(arguments, this@MainFragment.lifecycle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun getLayoutId(): Int = R.layout.fragment_main

    override fun showError(errMsg: String) {
        main_view?.longSnackbar(errMsg)
    }

    override fun setYearKm(km: String) {
        fragment_main_strava_recent_year.text = getString(R.string.main_fragment_strava_recent_year, km)
    }

    override fun set4WeekKm(km: String) {
        fragment_main_strava_recent_week.text = getString(R.string.main_fragment_strava_recent_week, km)
    }

    override fun setTotalKMNow(km: String) {
        fragment_main_mining.text = getString(R.string.main_fragment_mining_current, km)
    }

    override fun setRankingPer(rankingPer: String) {
        fragment_main_txt_rank_per.text = getString(R.string.main_fragment_ranking_per, rankingPer)
    }

    override fun setRemainDays(remainDays: String) {
        fragment_main_txt_remain_days.text = getString(
            R.string.main_fragment_remain_days,
            remainDays
        )
    }

    //-----------------

    override fun setRoundJoinCheck(check: Double) {
        if (check == 0.0) {
            fragment_main_mining_active_check.text = getString(R.string.main_fragment_mining_active_check_0)
        }else{
            fragment_main_mining_active_check.text = getString(R.string.main_fragment_mining_active_check_1)
        }
    }

    override fun setRoundLastDay(date: Double) {
        fragment_main_mining_active_roundLastDay.text = getString(R.string.main_fragment_mining_active_lastDay, date.toString())
    }

    override fun setRoundDistance(km: Any) {
        fragment_main_mining_active_roundDistance.text = getString(R.string.main_fragment_mining_active_lastDistance,String.format("%.2f", km))
    }

    override fun setRoundRank(rank: String, total:String) {
        fragment_main_mining_active_roundRank.text = getString(R.string.main_fragment_mining_active_rank,rank ,total)
    }

    override fun setRoundPrize(prize: String) {
        fragment_main_mining_active_roundPrize.text = getString(R.string.main_fragment_mining_active_prize ,prize)
    }
}