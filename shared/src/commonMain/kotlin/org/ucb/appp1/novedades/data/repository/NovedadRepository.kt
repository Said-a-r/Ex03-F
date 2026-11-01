package org.ucb.appp1.novedades.data.repository

import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.novedades.data.dao.NovedadDao
import org.ucb.appp1.novedades.data.entity.TablaNovedad

class NovedadRepository(private val dao: NovedadDao) {
    fun getAll(): Flow<List<TablaNovedad>> = dao.getAll()

    suspend fun insert(novedad: TablaNovedad) {
        dao.insert(novedad)
    }

    suspend fun insertAll(list: List<TablaNovedad>) {
        dao.insertAll(list)
    }

    suspend fun getLatest(): TablaNovedad? = dao.getLatest()

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}
