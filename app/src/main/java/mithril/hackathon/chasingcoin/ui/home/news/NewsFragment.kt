package mithril.hackathon.chasingcoin.ui.home.news

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_news.*
import mithril.hackathon.chasingcoin.R
import mithril.hackathon.chasingcoin.di.Injection
import mithril.hackathon.chasingcoin.ui.base.BaseFragment
import mithril.hackathon.chasingcoin.utils.Constants
import java.util.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun setTitle(title: String?) {

    }

    override fun setReward(reward: Long) {
    }

    override fun setDistance(distance: Long) {
    }

    override fun setNews(title: String? ,count: String?, km: String?, total: String?, getNum: String?, am: Date?, pm: Date?) {
//        getString(R.string.fragment_news_title, title,count,km,total,getNum,am,pm)
        fragment_news_title.text = " 恭喜上屆 "+title+" 參加者共 " + count + " 位, 以 "+km+" KM的成績贏得冠軍. 該回合的總金額合計有"+ total +"Mith 每人可領取"+ getNum+"Mith." +
                " 賽程日期起"+ am +"AM ~ "+pm+" PM"
    }
}