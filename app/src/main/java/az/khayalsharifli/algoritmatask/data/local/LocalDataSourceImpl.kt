package az.khayalsharifli.algoritmatask.data.local

import az.khayalsharifli.algoritmatask.model.LocalDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDataSourceImpl(private val dao: LocalDao) : LocalDataSource {
    override fun observeData(): Flow<List<LocalDto>> {
        return dao.getData()
    }

    override suspend fun insertData(dataList: List<LocalDto>) {
        withContext(Dispatchers.IO) {
            dao.clearData()
            dao.insertData(dataList)
        }
    }
}