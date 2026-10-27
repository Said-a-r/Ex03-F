package org.ucb.appp1.novedades.data.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.novedades.data.entity.TablaNovedad

@Dao
interface NovedadDao {
    @Query("SELECT * FROM tabla_novedad ORDER BY timestamp DESC")
    fun getAll(): Flow<List<TablaNovedad>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(novedad: TablaNovedad)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(novedades: List<TablaNovedad>)

    @Query("SELECT * FROM tabla_novedad ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLatest(): TablaNovedad?

    @Query("DELETE FROM tabla_novedad")
    suspend fun deleteAll()
}
