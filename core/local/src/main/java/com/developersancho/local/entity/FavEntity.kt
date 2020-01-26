package com.developersancho.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Fav_Table")
class FavEntity(
    @PrimaryKey
    var favId: Int = 0,
    var isFav: Boolean = false
)