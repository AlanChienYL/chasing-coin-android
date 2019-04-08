package mithril.hackathon.chasingcoin.ui.login

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_login.*
import mithril.hackathon.chasingcoin.R
import mithril.hackathon.chasingcoin.di.Injection
import mithril.hackathon.chasingcoin.ui.base.BaseActivity
import mithril.hackathon.chasingcoin.utils.Constants

/**
 * Created by AlanChien on 07,April,2019.
 */
class LoginActivity : BaseActivity(), LoginContract.View {


    override fun getLayoutId(): Int = R.layout.activity_login

    private val presenter: LoginPresenter<LoginContract.View>
            by lazy { LoginPresenter<LoginContract.View>() }

    override fun initializePresenter() = with(presenter) {
        setView(this@LoginActivity)
        dataInteractor = Injection.provideDataInteractor(
            Injection.providePrefsHelper(this@LoginActivity, Constants.SharePreferences.SPFS_NAME)
        )
        initialize(intent.extras, this@LoginActivity.lifecycle)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun setupWebView() {
        with(activity_login_wv) {
            settings.javaScriptEnabled = true
            settings.builtInZoomControls = false
            settings.setSupportZoom(false)
            removeJavascriptInterface("searchBoxJavaBridge_")
            removeJavascriptInterface("accessibility")
            removeJavascriptInterface("accessibilityTraversal")
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    presenter.pageStart()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    presenter.pageFinish()
                }

                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    request?.let { req ->
                        return presenter.shouldOverrideUrlLoading(req.url)
                    }
                    return super.shouldOverrideUrlLoading(view, request)
                }

            }
        }
    }

    override fun loadLoginUrl(url: String) {
        activity_login_wv.apply { loadUrl(url) }
    }

    override fun loginSuccess() {
        setResult(RESULT_OK)
        finish()
    }
}