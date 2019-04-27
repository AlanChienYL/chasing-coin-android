package mithril.hackathon.chasingcoin.ui.home.main

import mithril.hackathon.chasingcoin.ui.base.BaseView
import mithril.hackathon.chasingcoin.ui.base.IErrorView
import java.util.*

/**
 * Created by AlanChien on 09,April,2019.
 */
class MainContract {
    interface View : BaseView, IErrorView {
        fun setYearKm(km: String)

        fun set4WeekKm(km: String)

        fun setTotalKMNow(km: String)

        fun setRankingPer(rankingPer: String)

        fun setRemainDays(remainDays: String)

        fun setRoundJoinCheck(check :Int)

        fun setRoundLastDay(date :Double)

        fun setRoundDistance(km: Any)

        fun setRoundRank(rank:String , total:String)

        fun setRoundPrize(prize:String)

    }

    interface Presenter {

        fun onViewCreated()
    }
}