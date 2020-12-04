package fr.esimed.lebonangle.api



import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConnect {
    companion object {

        private const val BASE_URL = "http://10.0.2.2:8000/api/"

        private fun setTimeOutOfSocketOkHttpClient(): OkHttpClient {

            return OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .build()
        }

        private val api: ApiRequests = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(setTimeOutOfSocketOkHttpClient())
                .build()
                .create(ApiRequests::class.java)

        fun connect(): ApiRequests {
            return api
        }

    }
}