package mithril.hackathon.chasingcoin.ui.home.main

import mithril.hackathon.chasingcoin.ui.base.BaseView
import mithril.hackathon.chasingcoin.ui.base.IErrorView

/**
 * Created by AlanChien on 09,April,2019.
 */
class MainContract {
    interface View : BaseView, IErrorView {
        fun setYearKm(km: String)

        fun set4WeekKm(km: String)

        fun setTotalKMNow(km:String)
    }

    interface Presenter {

        fun onViewCreated()
    }
}