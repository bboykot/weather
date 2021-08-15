package android.example.com.weather.root

import android.example.com.weather.R
import android.widget.ImageView
import com.bumptech.glide.Glide

interface ImgWorker {

    fun loadWithGlide(imageView: ImageView, imgName: String){
        Glide
            .with(imageView)
            .load("https://openweathermap.org/img/wn/"+ imgName +".png")
            .error(R.drawable.ic_baseline_broken_image_24)
            .fallback(R.drawable.ic_baseline_image_not_supported_24)
            .into(imageView)
    }
}