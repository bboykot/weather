package android.example.com.weather.data

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

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
        val date get() = Date(dt * 1000)
        val format get() = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.US)
        val dateTime get() = format.format(date)

        data class Main(val temp: Float){
            val temperature: String get() = "Температура: " +temp.toInt().toString()+" C"
        }
        data class Weather(val main: String, val description: String)
        data class Wind(val speed: Float){
            val wind: String get()= "Ветер: " + speed.toInt().toString() + " м/с"
        }
    }
}
