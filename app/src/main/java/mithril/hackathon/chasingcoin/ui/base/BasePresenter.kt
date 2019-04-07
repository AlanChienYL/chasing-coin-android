package mithril.hackathon.chasingcoin.ui.base

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import mithril.hackathon.chasingcoin.data.DataInteractor
import kotlin.coroutines.CoroutineContext

/**
 * Created by AlanChien on 07,April,2019.
 */

abstract class BasePresenter<V : BaseView> : IBasePresenter<V>, CoroutineScope {

    private val job = SupervisorJob()

    open var dataInteractor: DataInteractor? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private var view: V? = null

    var lifecycle: Lifecycle? = null

    override fun initialize(extras: Bundle?, lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    override fun setView(view: V) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun destroy() {
        coroutineContext.cancelChildren()
    }

}