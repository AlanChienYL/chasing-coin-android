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
                contestants(news.count.toString())
                desc(
                    "上屆 ${news.title} ,以 ${String.format(
                        "%.2f",
                        news.winnerDistance
                    )} KM 贏得冠軍. 總金額合計有 ${news.totalDonation} Mith.每人可領 ${news.reward} Mith."
                )
                date("${news.startAt.getDateMinOnly()} - ${news.closeAt.getDateMinOnly()}")
            }
        }
    }

}