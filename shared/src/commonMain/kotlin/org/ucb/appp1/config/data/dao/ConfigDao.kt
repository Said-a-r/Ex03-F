package org.ucb.appp1.config.data.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.config.data.entity.ConfigItem

@Dao
interface ConfigDao {
    @Query("SELECT * FROM config_items")
    fun getAll(): Flow<List<ConfigItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ConfigItem)

    @Query("DELETE FROM config_items")
    suspend fun deleteAll()

    @Query("DELETE FROM config_items WHERE id = :id")
    suspend fun deleteById(id: Long)
}
