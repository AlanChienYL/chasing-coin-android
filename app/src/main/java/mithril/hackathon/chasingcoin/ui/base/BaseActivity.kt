package mithril.hackathon.chasingcoin.ui.base

import android.os.Bundle
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


    override fun onClick(p0: View?) {
        p0?.isEnabled = false
        p0?.postDelayed({ p0.isEnabled = true }, 350)
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