package developersancho.mvvm.db

import androidx.room.RoomDatabase
import developersancho.mvvm.dao.FavDao

abstract class CoreDatabase: RoomDatabase() {
    abstract fun favDao(): FavDao
}