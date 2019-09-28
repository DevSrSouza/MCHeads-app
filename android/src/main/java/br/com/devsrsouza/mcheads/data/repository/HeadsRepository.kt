package br.com.devsrsouza.mcheads.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import br.com.devsrsouza.mcheads.client.HeadApi
import br.com.devsrsouza.mcheads.client.HeadApiService
import br.com.devsrsouza.mcheads.data.database.HeadsDatabase
import br.com.devsrsouza.mcheads.data.database.asDomainModel
import br.com.devsrsouza.mcheads.data.domain.Head
import br.com.devsrsouza.mcheads.data.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HeadsRepository(val database: HeadsDatabase) {
    val heads: LiveData<List<Head>> = Transformations.map(database.headDao.getHeads()) {
        it.asDomainModel()
    }

    suspend fun refreshHeads() {
        withContext(Dispatchers.IO) {
            database.headDao.insertAll(*HeadApi.service.get(null, 1, -1).asDatabaseModel())
        }
    }
}