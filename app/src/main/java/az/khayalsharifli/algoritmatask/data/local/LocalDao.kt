package az.khayalsharifli.algoritmatask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import az.khayalsharifli.algoritmatask.model.LocalDto
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(list: List<LocalDto>)

    @Query("SELECT * FROM data")
    fun getData(): Flow<List<LocalDto>>

    @Query("DELETE FROM data")
    fun clearData()
}