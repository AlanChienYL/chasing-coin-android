package mithril.hackathon.chasingcoin.ui.home

import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home.*
import mithril.hackathon.chasingcoin.R
import mithril.hackathon.chasingcoin.di.Injection
import mithril.hackathon.chasingcoin.ui.base.BaseActivity
import mithril.hackathon.chasingcoin.utils.Constants

/**
 * Created by AlanChien on 09,April,2019.
 */
class HomeActivity : BaseActivity(), HomeContract.View, TabLayout.OnTabSelectedListener {


    override fun getLayoutId(): Int = R.layout.activity_home

    private val presenter: HomePresenter<HomeContract.View>
            by lazy { HomePresenter<HomeContract.View>() }

    override fun initializePresenter() = with(presenter) {
        setView(this@HomeActivity)
        dataInteractor = Injection.provideDataInteractor(
                Injection.providePrefsHelper(this@HomeActivity, Constants.SharePreferences.SPFS_NAME)
        )
        initialize(intent.extras, this@HomeActivity.lifecycle)
    }

    private val adapter by lazy { HomeAdapter(fragmentManager = supportFragmentManager) }

    override fun setAdapter() {
        activity_home_vp.apply {
            this.adapter = this@HomeActivity.adapter
            this.addOnPageChangeListener(
                    TabLayout.TabLayoutOnPageChangeListener(activity_home_tabs)
            )
        }
        activity_home_tabs.addOnTabSelectedListener(this)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        tab?.let { activity_home_vp.currentItem = it.position }
    }
}
