package mithril.hackathon.chasingcoin.ui.home.news

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_news.*
import mithril.hackathon.chasingcoin.R
import mithril.hackathon.chasingcoin.di.Injection
import mithril.hackathon.chasingcoin.ui.base.BaseFragment
import mithril.hackathon.chasingcoin.utils.Constants
import org.jetbrains.anko.design.longSnackbar

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

//    override fun showError(errMsg: String) {
//        news_view.longSnackbar(errMsg)
//    }

    override fun getLayoutId(): Int = R.layout.fragment_news

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun setTitle(tilte: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        fragment_news_title.text = getString(R.string.fragment_news_title, tilte)
    }

    override fun setReward(reward: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDistance(distance: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}