package mithril.hackathon.chasingcoin.ui.base

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by AlanChien on 07,April,2019.
 */
interface IBasePresenter<V : BaseView> : LifecycleObserver {

    fun initialize(extras: Bundle?, lifecycle: Lifecycle)

    fun setView(view: V)

    fun getView(): V?

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy()

}