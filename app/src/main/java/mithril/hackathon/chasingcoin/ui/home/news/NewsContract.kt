package mithril.hackathon.chasingcoin.ui.home.news

import mithril.hackathon.chasingcoin.ui.base.BaseView
import java.util.*

/**
 * Created by AlanChien on 09,April,2019.
 */
class NewsContract {
    interface View : BaseView {
        fun setTitle(title: String?)
        fun setReward(reward: Long)
        fun setDistance(distance: Long)
        fun setNews(title: String?, count: String?, km:String?, total:String? , getNum:String?,am:Date?,pm:Date?)
    }

    interface Presenter {

        fun onViewCreated()
    }
}