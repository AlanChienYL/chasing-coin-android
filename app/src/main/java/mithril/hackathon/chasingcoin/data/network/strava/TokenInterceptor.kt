package mithril.hackathon.chasingcoin.data.network.strava

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException

class TokenInterceptor(private val token: String?) : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        Timber.d("token : " + token)
        val request = chain.request().newBuilder().addHeader(
            "Authorization",
            "Bearer $token"
        ).build()
        return chain.proceed(request)
    }
}
