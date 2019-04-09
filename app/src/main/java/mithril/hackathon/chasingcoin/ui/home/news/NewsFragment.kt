package mithril.hackathon.chasingcoin.ui.home.news

import mithril.hackathon.chasingcoin.R
import mithril.hackathon.chasingcoin.di.Injection
import mithril.hackathon.chasingcoin.ui.base.BaseFragment
import mithril.hackathon.chasingcoin.utils.Constants

/**
 * Created by AlanChien on 09,April,2019.
 */
class NewsFragment : BaseFragment(), NewsContract.View {

    val presenter: NewsPresenter<NewsContract.View> by lazy { NewsPresenter<NewsContract.View>() }
    override fun initializePresenter() = with(presenter) {
        setView(this@NewsFragment)
        lifecycle = this@NewsFragment.lifecycle
        dataInteractor = Injection.provideDataInteractor(
                Injection.providePrefsHelper(
                        activity!!, Constants.SharePreferences.SPFS_NAME
                )
        )
        this@NewsFragment.lifecycle.addObserver(this)
    }


    override fun getLayoutId(): Int = R.layout.fragment_news

}