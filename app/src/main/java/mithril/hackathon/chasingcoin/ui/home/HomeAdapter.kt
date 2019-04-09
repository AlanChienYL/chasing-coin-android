package mithril.hackathon.chasingcoin.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import mithril.hackathon.chasingcoin.ui.base.BaseFragment
import mithril.hackathon.chasingcoin.ui.home.main.MainFragment
import mithril.hackathon.chasingcoin.ui.home.member.MemberFragment
import mithril.hackathon.chasingcoin.ui.home.news.NewsFragment

class HomeAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val pages: MutableList<BaseFragment>

    init {
        pages = arrayListOf(MainFragment(), NewsFragment(), MemberFragment())
    }

    override fun getItem(position: Int): Fragment = let { pages[position] }

    override fun getCount(): Int = let { pages.size }
}