package mithril.hackathon.chasingcoin.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import mithril.hackathon.chasingcoin.R
import mithril.hackathon.chasingcoin.di.Injection
import mithril.hackathon.chasingcoin.ui.base.BaseActivity
import mithril.hackathon.chasingcoin.utils.Constants

class MainActivity : BaseActivity(), MainContract.View {


    override fun getLayoutId(): Int = R.layout.activity_main

    private val presenter: MainPresenter<MainContract.View>
            by lazy { MainPresenter<MainContract.View>() }

    override fun initializePresenter() = with(presenter) {
        setView(this@MainActivity)
        dataInteractor = Injection.provideDataInteractor(
            Injection.getRepository(),
            Injection.providePrefsHelper(this@MainActivity, Constants.SharePreferences.SPFS_NAME)
        )
        initialize(intent.extras, this@MainActivity.lifecycle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun startStravaLogin(intentUri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW, intentUri)

        startActivity(intent)
    }
}
