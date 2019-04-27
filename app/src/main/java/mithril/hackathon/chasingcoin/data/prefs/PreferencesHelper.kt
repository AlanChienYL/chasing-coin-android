package mithril.hackathon.chasingcoin.data.prefs

import android.content.Context

/**
 * Created by AlanChien on 07,April,2019.
 */

class PreferencesHelper(
    ctx: Context,
    prefName: String
) {
    private val prefs = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    companion object {

        private var INSTANCE: PreferencesHelper? = null

        /**
         * Returns the single instance of this class, creating it if necessary.
         */
        @JvmStatic
        fun getInstance(ctx: Context, prefName: String): PreferencesHelper {
            return INSTANCE ?: PreferencesHelper(ctx, prefName)
                .apply { INSTANCE = this }
        }

        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }

        private const val STARVA_TOKEN = "mithril.hackathon.chasingcoin.prefs.stravaToken"
        private const val CHASING_TOKEN = "mithril.hackathon.chasingcoin.prefs.chasingToken"
        private const val REFRESH_TOKEN = "mithril.hackathon.chasingcoin.prefs.refreshToken"
        private const val STARVA_UID = "mithril.hackathon.chasingcoin.prefs.uid"
        private const val CHASER_UID = "mithril.hackathon.chasingcoin.prefs.chaserUid"
        private const val MITH_ACCESS_TOKEN = "mithril.hackathon.chasingcoin.prefs.mithAccessToken"
    }

    var chasingToken: String?
        get() = prefs.getString(CHASING_TOKEN, null)
        set(value) = prefs.edit().putString(CHASING_TOKEN, value).apply()

    var refreshToken: String?
        get() = prefs.getString(REFRESH_TOKEN, null)
        set(value) = prefs.edit().putString(REFRESH_TOKEN, value).apply()

    var stravaToken: String?
        get() = prefs.getString(STARVA_TOKEN, null)
        set(value) = prefs.edit().putString(STARVA_TOKEN, value).apply()

    var stravaUid: Long
        get() = prefs.getLong(STARVA_UID, 0)
        set(value) = prefs.edit().putLong(STARVA_UID, value).apply()

    var chaserUid: String?
        get() = prefs.getString(CHASER_UID, null)
        set(value) = prefs.edit().putString(CHASER_UID, value).apply()

    var mithToken: String?
        get() = prefs.getString(MITH_ACCESS_TOKEN, null)
        set(value) = prefs.edit().putString(MITH_ACCESS_TOKEN, value).apply()

}