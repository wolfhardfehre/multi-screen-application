package fontaine.nice.multiscreencontroller.network

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


private const val BASE_URL = "http://192.168.1.9:5000"
private const val TIMEOUT = 10L


interface ScreenApi {

    @GET("/toggle_screen")
    fun toggleScreen(@Query("on") on: Int,
                     @Query("shader") screen: Int): Observable<String>

    @GET("/change_shader")
    fun changeShader(@Query("shader") shader: Int): Observable<String>

    @GET("/reset_time")
    fun resetTime(@Query("shader") screen: Int): Observable<String>

    @GET("/reset_times")
    fun resetTimes(@Query("synced") synced: Int): Observable<String>

    @GET("/screen_span")
    fun screenSpan(@Query("mode") mode: Int): Observable<String>

    @GET("/manual")
    fun playMode(@Query("mode") mode: Int): Observable<String>

    companion object {
        fun create(): ScreenApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(
                    OkHttpClient.Builder()
                        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                        .addInterceptor(getLoggingInterceptor())
                        .build())
                .build()
            return retrofit.create(ScreenApi::class.java)
        }

        private fun getLoggingInterceptor() = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}
