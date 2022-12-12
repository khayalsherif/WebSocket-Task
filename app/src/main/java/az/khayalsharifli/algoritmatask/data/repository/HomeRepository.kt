package az.khayalsharifli.algoritmatask.data.repository

import az.khayalsharifli.algoritmatask.model.LocalDto
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun observeData(): Flow<List<LocalDto>>
    suspend fun sync(data: List<LocalDto>)
}