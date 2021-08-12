package android.example.com.weather.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CitiesEntity::class], version = 1, exportSchema = false)
abstract class CitiesDataBase: RoomDatabase() {
    abstract val citiesDao: CitiesDao

    companion object{
        @Volatile
        private var INSTANCE: CitiesDataBase? = null

        fun getInstance(context: Context): CitiesDataBase{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CitiesDataBase::class.java,
                        "cities_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}