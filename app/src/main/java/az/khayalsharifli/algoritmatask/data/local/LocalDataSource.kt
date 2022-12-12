package az.khayalsharifli.algoritmatask.data.local

import az.khayalsharifli.algoritmatask.model.LocalDto
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun observeData(): Flow<List<LocalDto>>
    suspend fun insertData(dataList: List<LocalDto>)
}