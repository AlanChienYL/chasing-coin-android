package mithril.hackathon.chasingcoin.ui.login

import android.net.Uri
import mithril.hackathon.chasingcoin.ui.base.BaseView

/**
 * Created by AlanChien on 07,April,2019.
 */
class LoginContract {
    interface View : BaseView {
        fun setupWebView()

        fun loadLoginUrl(url: String)

        fun loginSuccess()
    }

    interface Presenter {
        fun pageStart()

        fun pageFinish()

        fun shouldOverrideUrlLoading(url: Uri): Boolean
    }
}