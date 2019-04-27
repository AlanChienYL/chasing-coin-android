package mithril.hackathon.chasingcoin.ui.home.main

import mithril.hackathon.chasingcoin.ui.base.BaseView

/**
 * Created by AlanChien on 09,April,2019.
 */
class MemberContract {
    interface View : BaseView {
        fun setUserMithLevel(lv: String)

        fun setUserMithTotal(total: String)

        fun setUserMithStakedAmount(sAmount: String)

        fun setUserMithBalance(balance: String?)

    }

    interface Presenter {

        fun onViewCreated()
    }
}