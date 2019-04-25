package mithril.hackathon.chasingcoin.ui.home.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mithril.hackathon.chasingcoin.R
import mithril.hackathon.chasingcoin.data.network.server.response.GamesResp


class NewsAdapter(private val newsList: List<GamesResp.News>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemCount() = newsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder { return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))
        }

//        override fun getItemCount() = presenter.getBookListCount()

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        }
}
