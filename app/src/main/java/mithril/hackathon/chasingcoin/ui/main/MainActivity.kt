package mithril.hackathon.chasingcoin.ui.main

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import mithril.hackathon.chasingcoin.R
import mithril.hackathon.chasingcoin.di.Injection
import mithril.hackathon.chasingcoin.ui.base.BaseActivity
import mithril.hackathon.chasingcoin.ui.home.HomeActivity
import mithril.hackathon.chasingcoin.ui.login.LoginActivity
import mithril.hackathon.chasingcoin.utils.Constants
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity(), MainContract.View {

    override fun getLayoutId(): Int = R.layout.activity_main

    private val presenter: MainPresenter<MainContract.View>
            by lazy { MainPresenter<MainContract.View>() }

    override fun initializePresenter() = with(presenter) {
        setView(this@MainActivity)
        dataInteractor = Injection.provideDataInteractor(
                Injection.providePrefsHelper(this@MainActivity, Constants.SharePreferences.SPFS_NAME)
        )
        initialize(intent.extras, this@MainActivity.lifecycle)
        this@MainActivity.lifecycle.addObserver(presenter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initializeViewListener() {
        super.initializeViewListener()
        activity_main_bt_login.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        super.onClick(p0)
        when (p0?.id) {
            R.id.activity_main_bt_login -> presenter.clickToLogin()
        }
    }

    override fun navigateToLogin() {
        startActivity<LoginActivity>()
    }

    override fun navigateToHome() {
        startActivity(intentFor<HomeActivity>().clearTask().newTask())
    }

    override fun setToken(token: String) {
        activity_main_bt_login.text = token
    }

}
