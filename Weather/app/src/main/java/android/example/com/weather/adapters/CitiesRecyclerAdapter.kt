package android.example.com.weather.adapters

import android.example.com.weather.R
import android.example.com.weather.cities.CitiesViewModel
import android.example.com.weather.data.ForecastCurrent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CitiesRecyclerAdapter (val forecastCurrent: List<ForecastCurrent>,val viewModel: CitiesViewModel) : RecyclerView.Adapter<CitiesRecyclerAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvName: TextView
        val tvTemp: TextView
        val tvWind: TextView
        val tvWeather: TextView
        val btnForecastDay: Button
        val btnForecastWeek: Button
        val btnSetDefault: Button

        init {
            tvName = itemView.findViewById(R.id.rec_cities_tv_name)
            tvTemp = itemView.findViewById(R.id.rec_cities_tv_temp)
            tvWind = itemView.findViewById(R.id.rec_cities_tv_wind)
            tvWeather = itemView.findViewById(R.id.rec_cities_tv_weather)
            btnForecastDay = itemView.findViewById(R.id.rec_cities_btn_forecast_day)
            btnForecastWeek = itemView.findViewById(R.id.rec_cities_btn_forecast_week)
            btnSetDefault = itemView.findViewById(R.id.rec_cities_btn_set_default)

            btnSetDefault.setOnClickListener {
                viewModel.setNewDefaultCity(forecastCurrent[adapterPosition].id)
            }
        }
    }

    override fun getItemCount() = forecastCurrent.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLayout = LayoutInflater.from(parent.context).inflate(R.layout.cities_recycler_item,parent,false)
        return MyViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text = forecastCurrent[position].name
        holder.tvTemp.text = forecastCurrent[position].main.temperature
        holder.tvWind.text = forecastCurrent[position].wind.wind
        holder.tvWeather.text = forecastCurrent[position].weather[0].description
    }
}