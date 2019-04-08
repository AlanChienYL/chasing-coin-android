package mithril.hackathon.chasingcoin.di

import android.content.Context
import mithril.hackathon.chasingcoin.data.DataInteractor
import mithril.hackathon.chasingcoin.data.network.server.ServerApiClient
import mithril.hackathon.chasingcoin.data.prefs.PreferencesHelper

/**
 * Created by AlanChien on 09,January,2019.
 */
class Injection {

    companion object {

        fun provideDataInteractor(apiclient: ServerApiClient, prefsHelper: PreferencesHelper): DataInteractor {
            return DataInteractor(apiclient, prefsHelper)
        }

        fun providePrefsHelper(ctx: Context, prefName: String): PreferencesHelper {
            return PreferencesHelper.getInstance(ctx, prefName)
        }

        fun getRepository(): ServerApiClient {
            return ServerApiClient.getInstance()
        }
    }
}