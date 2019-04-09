package mithril.hackathon.chasingcoin.ui.home.member

import mithril.hackathon.chasingcoin.R
import mithril.hackathon.chasingcoin.di.Injection
import mithril.hackathon.chasingcoin.ui.base.BaseFragment
import mithril.hackathon.chasingcoin.ui.home.main.MemberContract
import mithril.hackathon.chasingcoin.ui.home.main.MemberPresenter
import mithril.hackathon.chasingcoin.utils.Constants

/**
 * Created by AlanChien on 09,April,2019.
 */
class MemberFragment : BaseFragment(), MemberContract.View {

    val presenter: MemberPresenter<MemberContract.View> by lazy { MemberPresenter<MemberContract.View>() }

    override fun initializePresenter() = with(presenter) {
        setView(this@MemberFragment)
        lifecycle = this@MemberFragment.lifecycle
        dataInteractor = Injection.provideDataInteractor(
                Injection.providePrefsHelper(
                        activity!!, Constants.SharePreferences.SPFS_NAME
                )
        )
        this@MemberFragment.lifecycle.addObserver(this)
    }


    override fun getLayoutId(): Int = R.layout.fragment_member

}