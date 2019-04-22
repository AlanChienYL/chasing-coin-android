package mithril.hackathon.chasingcoin.ui.home.news

import mithril.hackathon.chasingcoin.data.network.interactor.NewsInteractor
import mithril.hackathon.chasingcoin.data.network.server.response.BaseResp
import mithril.hackathon.chasingcoin.data.network.server.response.GamesResp

import mithril.hackathon.chasingcoin.ui.base.BasePresenter
import timber.log.Timber

/**
 * Created by Kenneth on 22,April,2019.
 */
class NewsPresenter<V : NewsContract.View> : BasePresenter<V>(), NewsContract.Presenter {

    private val serverStatsInter by lazy {
       NewsInteractor(
            dataInteractor!!, ::apiGamesSuccess, ::apiGamesFailed
        )
    }


    override fun create() {

    }
    private fun apiGamesFailed(resp: BaseResp?) {
        getView()?.hideProgress()
        Timber.e("in apiGamesFailed ${resp?.error}. code : ${resp?.code}")
    }

    private fun apiGamesSuccess(resp: GamesResp) {
        getView()?.hideProgress()

        println("==================="  + resp.data)

        println("==================="  + resp.data!!.count)
        println("==================="  + resp.data!!.news.get(0).title)

        getView()?.setTitle(resp.data!!.news?.get(0)!!.title)
    }

    override fun onViewCreated() {
        getView()?.showProgress()
//        val myAdapter = ArrayAdapter<String>(, R.layout.simple_list_item_1)
//        myAdapter.addAll("三個鉛筆", "四個腳踏車")
        serverStatsInter.getNews()

    }
}