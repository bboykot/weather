package android.example.com.weather.data

import com.google.gson.annotations.SerializedName

data class ForecastDay(
    val cod: Int,
    val city: City,
    val list: List<HourData>
)
{
    data class City(
        val id: Long,
        val name: String
    )
    data class HourData(
        val dt: Long,
        @SerializedName("dt_txt") val dateText: String,
        val main: Main,
        val weather: List<Weather>,
        val wind: Wind
    ){
        data class Main(val temp: Float)
        data class Weather(val main: String, val description: String)
        data class Wind(val speed: Float)
    }
}
