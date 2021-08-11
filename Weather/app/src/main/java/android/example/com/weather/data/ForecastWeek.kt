package android.example.com.weather.data

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
        data class Temp(val day: Float)
        data class Weather(val description: String, val main: String)
    }
}
