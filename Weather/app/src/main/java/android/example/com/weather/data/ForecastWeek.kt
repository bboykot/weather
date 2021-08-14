package android.example.com.weather.data

import java.text.SimpleDateFormat
import java.util.*

data class ForecastWeek(
    val cnt: Int,
    val city: City,
    val list: List<DayData>
){
    data class City(
        val id: Long,
        val name: String
    )
    data class DayData(
        val dt: Long,
        val speed: Float,
        val temp: Temp,
        val weather: List<Weather>
    ){
        val wind: String get()= "Ветер: " + speed.toInt().toString() + " м/с"

        val date get() = Date(dt * 1000)
        val format get() = SimpleDateFormat("dd.MM.yyyy",Locale.US)
        val dateTime get() = format.format(date)


        data class Temp(val day: Float){
            val temperature: String get() = "Температура: " +day.toInt().toString()+" C"
        }
        data class Weather(val description: String, val main: String)
    }
}
