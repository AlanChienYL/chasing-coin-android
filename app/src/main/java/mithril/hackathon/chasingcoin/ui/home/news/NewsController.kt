package mithril.hackathon.chasingcoin.ui.home.news

import com.airbnb.epoxy.EpoxyController
import mithril.hackathon.chasingcoin.data.network.server.response.GamesResp
import mithril.hackathon.chasingcoin.ui.home.news.models.news
import mithril.hackathon.chasingcoin.utils.getDateMinOnly

/**
 * Created by AlanChien on 26,April,2019.
 */
class NewsController(val news: MutableList<GamesResp.News>) : EpoxyController() {

    override fun buildModels() {
        news.forEachIndexed { index, news ->
            news {
                id("news $index ${news.marathonId}")
                news.title?.let {
                    title(it)
                }

                desc(
                    " 恭喜上屆 ${news.title} 參加者共 ${news.count} 位, 以 ${String.format(
                        "%.2f",
                        news.winnerDistance
                    )} KM的成績贏得冠軍. 該回合的總金額合計有 ${news.totalDonation} Mith 每人可領取 ${news.reward} Mith." +
                            " 賽程日期起 ${news.startAt.getDateMinOnly()} ~ ${news.closeAt.getDateMinOnly()}"
                )
            }
        }
    }

}