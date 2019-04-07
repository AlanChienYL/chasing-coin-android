package mithril.hackathon.chasingcoin.ui.base

import android.os.Bundle
import android.os.SystemClock
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import mithril.hackathon.chasingcoin.R


abstract class BaseActivity : AppCompatActivity(), BaseView, IErrorView, View.OnClickListener {

    abstract fun getLayoutId(): Int

    abstract fun initializePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initializeViewListener()
        initializePresenter()
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
        llLoading = findViewById(R.id.llLoading)
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showError(errMsg: String) {
    }

    override fun logout() {

    }
}