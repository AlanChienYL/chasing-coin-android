package mithril.hackathon.chasingcoin.ui.home.news.models

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.item_news.view.*
import mithril.hackathon.chasingcoin.R

/**
 * Created by AlanChien on 26,April,2019.
 */
@EpoxyModelClass(layout = R.layout.item_news)
abstract class NewsModel : EpoxyModelWithHolder<NewsModel.NewsHolder>() {

    @EpoxyAttribute
    var title: String = ""

    @EpoxyAttribute
    var desc: String = ""

    override fun bind(holder: NewsHolder) {
        super.bind(holder)
        holder.apply {
            txtTitle.text = title
            txtDesc.text = desc
        }
    }

    inner class NewsHolder : EpoxyHolder() {
        lateinit var txtTitle: TextView
        lateinit var txtDesc: TextView
        override fun bindView(itemView: View) {
            itemView.run {
                txtTitle = item_news_list_title
                txtDesc = item_news_list_description
            }
        }
    }
}