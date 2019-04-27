package mithril.hackathon.chasingcoin.ui.home.member

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_member.*
import mithril.hackathon.chasingcoin.R
import mithril.hackathon.chasingcoin.di.Injection
import mithril.hackathon.chasingcoin.ui.base.BaseFragment
import mithril.hackathon.chasingcoin.ui.home.main.MemberContract
import mithril.hackathon.chasingcoin.ui.home.main.MemberPresenter
import mithril.hackathon.chasingcoin.utils.Constants
import org.jetbrains.anko.design.longSnackbar

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
        initialize(arguments, this@MemberFragment.lifecycle)
    }


    override fun getLayoutId(): Int = R.layout.fragment_member

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }


    override fun setUserMithLevel(lv: String) {
        fragment_member_level.text =   getString(R.string.member_level, lv)
    }

    override fun setUserMithTotal(total: String) {
        fragment_member_total.text =  getString(R.string.member_total, total)
    }

    override fun setUserMithStakedAmount(sAmount: String) {
        fragment_member_amount.text = getString(R.string.member_amount, sAmount)
    }

    override fun setUserMithBalance(balance: String?) {
        fragment_member_balance.text = getString(R.string.member_balance, balance)
    }
}