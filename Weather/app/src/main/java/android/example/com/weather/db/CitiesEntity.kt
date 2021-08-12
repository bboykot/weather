package android.example.com.weather.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CitiesEntity(
    @PrimaryKey
    val id: Long?,
    val name: String?,
    val defaultCity: Boolean = false
)
