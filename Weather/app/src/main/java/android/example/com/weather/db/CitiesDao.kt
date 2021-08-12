package android.example.com.weather.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CitiesEntity)

    @Query("select * from cities")
    suspend fun selectCities(): List<CitiesEntity>

    @Query("select * from cities where defaultCity = :defaultCity")
    suspend fun selectDefaultCity(defaultCity : Boolean): CitiesEntity

    @Query("update cities set defaultCity= :defaultCity where id = :id")
    suspend fun removeDefaultFlag(defaultCity: Boolean, id: Long?)
}