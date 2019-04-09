package mithril.hackathon.chasingcoin.ui.base

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import mithril.hackathon.chasingcoin.R

abstract class BaseFragment : Fragment(), BaseView, OnClickListener {

    abstract fun initializePresenter()

    abstract fun getLayoutId(): Int

    open fun setTitle() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializePresenter()
    }

    open lateinit var ctx: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context?.let {
            ctx = it
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return when (view == null) {
            true -> inflater.inflate(getLayoutId(), container, false)
            false -> view
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewListener()
    }

    override fun showProgress() {
        llLoading?.let {
            it.visibility = View.VISIBLE
        }
    }

    override fun hideProgress() {
        llLoading?.let {
            it.visibility = View.GONE
        }
    }

    private var llLoading: LinearLayout? = null
    open fun initializeViewListener() {
        llLoading = view?.findViewById(R.id.llLoading)
    }

    private var lastClickTime: Long = 0
    override fun onClick(p0: View?) {
        SystemClock.elapsedRealtime().let {
            if (it - lastClickTime < 1300) {
                return
            }
            lastClickTime = it
        }
    }

    override fun logout() {
        activity?.let {
            when (it) {
                is BaseActivity -> it.logout()
            }
        }
    }
}