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

        private const val STARVA_TOKEN = "mithril.hackathon.chasingcoin.prefs.token"
    }

    var token: String?
        get() = prefs.getString(STARVA_TOKEN, null)
        set(value) = prefs.edit().putString(STARVA_TOKEN, value).apply()

}