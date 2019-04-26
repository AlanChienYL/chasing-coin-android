package mithril.hackathon.chasingcoin.ui.home.news

import kotlinx.coroutines.Job
import mithril.hackathon.chasingcoin.data.network.interactor.NewsInteractor
import mithril.hackathon.chasingcoin.data.network.server.response.BaseResp
import mithril.hackathon.chasingcoin.data.network.server.response.GamesResp
import mithril.hackathon.chasingcoin.ui.base.BasePresenter
import timber.log.Timber

/**
 * Created by Kenneth on 22,April,2019.
 */
class NewsPresenter<V : NewsContract.View> : BasePresenter<V>(), NewsContract.Presenter {

    private lateinit var job: Job
    private lateinit var resp: GamesResp
    private val serverStatsInter by lazy {
        NewsInteractor(
            dataInteractor!!, ::apiGamesSuccess, ::apiGamesFailed
        )
    }


    override fun create() {
        job = serverStatsInter.getNews()
    }

    private fun apiGamesFailed(resp: BaseResp?) {
        getView()?.hideProgress()
        Timber.e("in apiGamesFailed ${resp?.error}. code : ${resp?.code}")
        resp?.error?.let { err ->
            getView()?.setTitle("apiGamesFailed")
        }
        when (resp?.code == 401) {
            true -> print("=== apiGamesFailed === ")
            else -> getView()?.hideProgress()
        }
    }

    private fun apiGamesSuccess(resp: GamesResp) {
        this.resp = resp
        Timber.e("${getView()}")
        getView()?.hideProgress()
        getView()?.setController(NewsController(resp.data!!.news))
//        getView()?.setNews(
//            resp.data!!.news[0].title,
//            String.format("%.2f", resp.data.count),
//            String.format("%.2f", resp.data.news[0].winnerDistance),
//            resp.data.news.get(0).totalDonation.toString(),
//            resp.data.news[0].reward.toString(),
//            Date(resp.data.news[0].startAt),
//            Date(resp.data.news[0].closeAt)
//        )


    }

    override fun onViewCreated() {
        takeIf { job.isActive }?.let {
            getView()?.showProgress()
        }
        takeIf { ::resp.isInitialized }?.let {
            getView()?.setController(NewsController(resp.data!!.news))
        }
    }
}