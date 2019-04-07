package mithril.hackathon.chasingcoin.data.network.strava

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import mithril.hackathon.chasingcoin.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by AlanChien on 07,April,2019.
 */
class ApiClient {

    fun getService(): IApiService = provideRetrofit().create(IApiService::class.java)

    companion object {
        private var INSTANCE: ApiClient? = null

        /**
         * Returns the single instance of this class, creating it if necessary.
         */
        @JvmStatic
        fun getInstance(): ApiClient {
            return INSTANCE ?: ApiClient().apply { INSTANCE = this }
        }

        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }

        const val BASE_URL = "https://www.strava.com/"
        const val TIMEOUT_CONNECTION: Long = 3
        const val TIMEOUT_READ: Long = 15
    }


    private fun provideRetrofit() = Retrofit.Builder().apply {
        addCallAdapterFactory(CoroutineCallAdapterFactory())
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(BASE_URL)
        client(getOkHttpBuilder().build())
    }.build()

    private fun getOkHttpBuilder(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        if (!BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.NONE
        } else {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(logging)
        builder.connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
        builder.readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
        return builder
    }
}