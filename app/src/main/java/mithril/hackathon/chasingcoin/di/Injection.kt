package mithril.hackathon.chasingcoin.di

import android.content.Context
import mithril.hackathon.chasingcoin.data.DataInteractor
import mithril.hackathon.chasingcoin.data.network.strava.ApiClient
import mithril.hackathon.chasingcoin.data.prefs.PreferencesHelper

/**
 * Created by AlanChien on 09,January,2019.
 */
class Injection {

    companion object {

        fun provideDataInteractor(apiclient: ApiClient, prefsHelper: PreferencesHelper): DataInteractor {
            return DataInteractor(apiclient, prefsHelper)
        }

        fun providePrefsHelper(ctx: Context, prefName: String): PreferencesHelper {
            return PreferencesHelper.getInstance(ctx, prefName)
        }

        fun getRepository(): ApiClient {
            return ApiClient.getInstance()
        }
    }
}