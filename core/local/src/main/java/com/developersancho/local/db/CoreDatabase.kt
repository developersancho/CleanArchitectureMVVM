package com.developersancho.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.developersancho.local.dao.FavDao
import com.developersancho.local.entity.FavEntity

@Database(
    entities = [FavEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CoreDatabase : RoomDatabase() {
    abstract fun favDao(): FavDao
}