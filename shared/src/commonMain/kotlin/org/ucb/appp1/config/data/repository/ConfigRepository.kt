package org.ucb.appp1.config.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import org.ucb.appp1.config.data.dao.ConfigDao
import org.ucb.appp1.config.data.entity.ConfigItem

class ConfigRepository(private val configDao: ConfigDao) {
    fun getAll(): Flow<List<ConfigItem>> = configDao.getAll()

    suspend fun insert(item: ConfigItem) {
        configDao.insert(item)
    }

    suspend fun deleteAll() {
        configDao.deleteAll()
    }

    suspend fun deleteById(id: Long) {
        configDao.deleteById(id)
    }

    suspend fun seedInitialDataIfNeeded() {
        val currentList = configDao.getAll().first()
        if (currentList.isEmpty()) {
            configDao.insert(ConfigItem(clave = "API_URL", valor = "https://api.ucb.edu.bo", timestamp = System.currentTimeMillis()))
            configDao.insert(ConfigItem(clave = "TIMEOUT_SECONDS", valor = "30", timestamp = System.currentTimeMillis()))
            configDao.insert(ConfigItem(clave = "FEATURE_DARK_MODE", valor = "true", timestamp = System.currentTimeMillis()))
        }
    }
}
