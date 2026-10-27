package org.ucb.appp1.config

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor
import org.ucb.appp1.config.data.dao.ConfigDao
import org.ucb.appp1.config.data.entity.ConfigItem
import org.ucb.appp1.exchange.data.dao.DollarDao
import org.ucb.appp1.exchange.data.entity.DollarEntity
import org.ucb.appp1.novedades.data.dao.NovedadDao
import org.ucb.appp1.novedades.data.entity.TablaNovedad

@Database(entities = [ConfigItem::class, DollarEntity::class, TablaNovedad::class], version = 3)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun configDao(): ConfigDao
    abstract fun dollarDao(): DollarDao
    abstract fun novedadDao(): NovedadDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
