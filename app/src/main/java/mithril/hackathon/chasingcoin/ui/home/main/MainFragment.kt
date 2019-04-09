package mithril.hackathon.chasingcoin.ui.home.main

import kotlinx.android.synthetic.main.fragment_main.*
import mithril.hackathon.chasingcoin.R
import mithril.hackathon.chasingcoin.di.Injection
import mithril.hackathon.chasingcoin.ui.base.BaseFragment
import mithril.hackathon.chasingcoin.utils.Constants
import org.jetbrains.anko.design.longSnackbar

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
        this@MainFragment.lifecycle.addObserver(this)
    }


    override fun getLayoutId(): Int = R.layout.fragment_main

    override fun showError(errMsg: String) {
        main_view.longSnackbar(errMsg)
    }
}