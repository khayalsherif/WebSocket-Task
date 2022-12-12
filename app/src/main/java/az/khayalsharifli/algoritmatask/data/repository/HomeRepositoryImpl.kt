package az.khayalsharifli.algoritmatask.data.repository

import az.khayalsharifli.algoritmatask.data.local.LocalDataSource
import az.khayalsharifli.algoritmatask.model.LocalDto
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(val dataSource: LocalDataSource) : HomeRepository {
    override fun observeData(): Flow<List<LocalDto>> {
        return dataSource.observeData()
    }

    override suspend fun sync(data: List<LocalDto>) {
        dataSource.insertData(data)
    }

}