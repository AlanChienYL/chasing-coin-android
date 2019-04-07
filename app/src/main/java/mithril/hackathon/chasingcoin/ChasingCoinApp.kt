package mithril.hackathon.chasingcoin

import android.app.Application
import timber.log.Timber

/**
 * Created by AlanChien on 07,April,2019.
 */
class ChasingCoinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}