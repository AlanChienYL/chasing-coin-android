package mithril.hackathon.chasingcoin.ui.main

import android.os.Bundle
import mithril.hackathon.chasingcoin.R
import mithril.hackathon.chasingcoin.ui.base.BaseActivity

class MainActivity : BaseActivity(), MainContract.View {

    override fun getLayoutId(): Int = R.layout.activity_main

    private val presenter: MainPresenter<MainContract.View>
            by lazy { MainPresenter<MainContract.View>() }

    override fun initializePresenter() = with(presenter) {
        setView(this@MainActivity)
        initialize(intent.extras, this@MainActivity.lifecycle)
        this@MainActivity.lifecycle.addObserver(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
