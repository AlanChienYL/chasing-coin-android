package mithril.hackathon.chasingcoin.data

import mithril.hackathon.chasingcoin.data.network.server.ServerApiClient
import mithril.hackathon.chasingcoin.data.network.strava.ApiClient
import mithril.hackathon.chasingcoin.data.prefs.PreferencesHelper

/**
 * Created by AlanChien on 09,January,2019.
 */
class DataInteractor(
    val apiClient: ApiClient,
    val serverApiClient: ServerApiClient,
    val prefsHelper: PreferencesHelper
) {
    val apiService by lazy { apiClient.getService() }
    val serverApiService by lazy { serverApiClient.getService() }
}