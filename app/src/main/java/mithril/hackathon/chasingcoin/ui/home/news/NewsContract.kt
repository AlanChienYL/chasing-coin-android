package mithril.hackathon.chasingcoin.ui.home.news

import mithril.hackathon.chasingcoin.ui.base.BaseView

/**
 * Created by AlanChien on 09,April,2019.
 */
class NewsContract {
    interface View : BaseView {
        fun setTitle(title: String?)
        fun setReward(reward: Long)
        fun setDistance(distance: Long)
    }

    interface Presenter {

        fun onViewCreated()
    }
}