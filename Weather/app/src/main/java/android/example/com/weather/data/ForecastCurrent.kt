package android.example.com.weather.data

data class ForecastCurrent(
    val name: String,
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind
){
    data class Weather(val main: String, val description: String)
    data class Main(val temp: Float){
        val temperature: String get() = "Температура: " +temp.toInt().toString()+" C"
    }
    data class Wind(val speed: Float){
        val wind: String get()= "Ветер: " + speed.toInt().toString() + " м/с"
    }
}
