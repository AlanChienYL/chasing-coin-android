package mithril.hackathon.chasingcoin.ui.home.news

import com.airbnb.epoxy.EpoxyController
import mithril.hackathon.chasingcoin.data.network.server.response.NewsResp
import mithril.hackathon.chasingcoin.ui.home.news.models.news
import mithril.hackathon.chasingcoin.utils.getDateMinOnly

/**
 * Created by AlanChien on 26,April,2019.
 */
class NewsController(val news: MutableList<NewsResp.News>) : EpoxyController() {

    override fun buildModels() {
        news.forEachIndexed { index, news ->
            news {
                id("news $index ${news.marathonId}")
                news.title?.let {
                    title(it)
                }
                publishat(news.publishAt.getDateMinOnly())
                desc(
                    "恭喜上屆${news.title}(每月最會跑)的贏家<font color=\"#198585\"><b> ${news.winners} </b></font>共 ${String.format("%.0f",news.count)} 人,以 ${String.format(
                        "%.2f",
                        news.winnerDistance
                    )} KM的成績贏得冠軍。該回合的總金額合計有 ${String.format("%.0f",news.totalDonation)} Mith，每人可領 ${news.reward} Mith."
                )
                date("${news.startAt.getDateMinOnly()} - ${news.closeAt.getDateMinOnly()}")
            }
        }
    }

}