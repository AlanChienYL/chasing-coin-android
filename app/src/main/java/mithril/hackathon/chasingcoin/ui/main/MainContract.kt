package mithril.hackathon.chasingcoin.ui.main

import android.net.Uri
import mithril.hackathon.chasingcoin.ui.base.BaseView
import mithril.hackathon.chasingcoin.ui.base.IErrorView

/**
 * Created by AlanChien on 07,April,2019.
 */
class MainContract {
    interface View : BaseView, IErrorView {
        fun startStravaLogin(intentUri: Uri)
    }

    interface Presenter {

    }
}