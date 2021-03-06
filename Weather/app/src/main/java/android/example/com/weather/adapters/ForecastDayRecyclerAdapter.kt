package android.example.com.weather.adapters

import android.example.com.weather.R
import android.example.com.weather.data.ForecastDay
import android.example.com.weather.root.ImgWorker
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ForecastDayRecyclerAdapter(val forecastDay: List<ForecastDay.HourData>) : RecyclerView.Adapter<ForecastDayRecyclerAdapter.MyViewHolder>() , ImgWorker{

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvDate: TextView
        val tvTemp: TextView
        val tvWind: TextView
        val tvWeather: TextView
        val weatherImg: ImageView

        init {
            tvDate = itemView.findViewById(R.id.rec_forecast_tv_date)
            tvTemp = itemView.findViewById(R.id.rec_forecast_tv_temp)
            tvWind = itemView.findViewById(R.id.rec_forecast_tv_wind)
            tvWeather = itemView.findViewById(R.id.rec_forecast_tv_weather)
            weatherImg = itemView.findViewById(R.id.rec_forecast_img)
        }
    }

    override fun getItemCount() = forecastDay.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLayout = LayoutInflater.from(parent.context).inflate(R.layout.forecast_recycler_item,parent,false)
        return MyViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTemp.text = forecastDay[position].main.temperature
        holder.tvDate.text = forecastDay[position].dateTime
        holder.tvWind.text = forecastDay[position].wind.wind
        holder.tvWeather.text = forecastDay[position].weather[0].description
        holder.weatherImg.apply {
            loadWithGlide(this,forecastDay[position].weather[0].icon)
        }
    }
}