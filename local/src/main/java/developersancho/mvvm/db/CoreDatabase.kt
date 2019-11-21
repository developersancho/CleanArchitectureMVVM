package developersancho.mvvm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import developersancho.mvvm.dao.FavDao
import developersancho.mvvm.entity.FavEntity


@Database(
    entities = [FavEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CoreDatabase: RoomDatabase() {
    abstract fun favDao(): FavDao
}