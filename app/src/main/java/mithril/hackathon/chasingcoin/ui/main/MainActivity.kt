package mithril.hackathon.chasingcoin.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import mithril.hackathon.chasingcoin.R
import mithril.hackathon.chasingcoin.di.Injection
import mithril.hackathon.chasingcoin.ui.base.BaseActivity
import mithril.hackathon.chasingcoin.ui.login.LoginActivity
import mithril.hackathon.chasingcoin.utils.Constants
import mithril.hackathon.chasingcoin.utils.Constants.Request.Companion.REQ_LOGIN
import org.jetbrains.anko.startActivityForResult

class MainActivity : BaseActivity(), MainContract.View {

    override fun getLayoutId(): Int = R.layout.activity_main

    private val presenter: MainPresenter<MainContract.View>
            by lazy { MainPresenter<MainContract.View>() }

    override fun initializePresenter() = with(presenter) {
        setView(this@MainActivity)
        dataInteractor = Injection.provideDataInteractor(
            Injection.getRepository(),
            Injection.providePrefsHelper(this@MainActivity, Constants.SharePreferences.SPFS_NAME)
        )
        initialize(intent.extras, this@MainActivity.lifecycle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun navigateToLogin() {
        startActivityForResult<LoginActivity>(REQ_LOGIN)
    }

    override fun setToken(token: String) {
        activity_main_tv.text = token
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> presenter.loginSuccess()
        }
    }
}
