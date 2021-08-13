package android.example.com.weather.adapters

import android.example.com.weather.R
import android.example.com.weather.data.ForecastWeek
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ForecastWeekRecyclerAdapter(val forecastWeek: List<ForecastWeek.DayData>) : RecyclerView.Adapter<ForecastWeekRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvDate: TextView
        val tvTemp: TextView
        val tvWind: TextView
        val tvWeather: TextView

        init {
            tvDate = itemView.findViewById(R.id.rec_forecast_tv_date)
            tvTemp = itemView.findViewById(R.id.rec_forecast_tv_temp)
            tvWind = itemView.findViewById(R.id.rec_forecast_tv_wind)
            tvWeather = itemView.findViewById(R.id.rec_forecast_tv_weather)
        }
    }

    override fun getItemCount()= forecastWeek.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLayout = LayoutInflater.from(parent.context).inflate(R.layout.forecast_recycler_item,parent,false)
        return MyViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvDate.text = forecastWeek[position].dt.toString()
        holder.tvTemp.text = forecastWeek[position].temp.temperature
        holder.tvWind.text = forecastWeek[position].wind
        holder.tvWeather.text = forecastWeek[position].weather[0].description
    }
}