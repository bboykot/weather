package android.example.com.weather.network

import android.example.com.weather.data.ForecastDay
import android.example.com.weather.data.ForecastWeek
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

private val retrofit =Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService{
    @GET("forecast?cnt=8&lang=ru&units=metric&appid=6493a6156cae804aa207eb9ee638c79b")
    suspend fun getDayForecast(@Query("q") name: String): ForecastDay

    @GET("forecast/daily?units=metric&cnt=7&lang=ru&appid=c0c4a4b4047b97ebc5948ac9c48c0559")
    suspend fun getWeekForecast(@Query("q") name: String): ForecastWeek
}
object WeatherApi{
    val retrofitService: WeatherApiService by lazy{
        retrofit.create(WeatherApiService::class.java)
    }
}