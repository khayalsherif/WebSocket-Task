package az.khayalsharifli.algoritmatask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import az.khayalsharifli.algoritmatask.model.LocalDto

@Database(
    entities = [LocalDto::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun dao(): LocalDao
}
