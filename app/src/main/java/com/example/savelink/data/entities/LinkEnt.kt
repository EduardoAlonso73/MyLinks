package com.example.savelink.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SaveLinkEnt")
data class LinkEnt(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var link: String,
    var category: String = "",
    var isSavaToBook:Boolean=false,
    var isFavorite: Boolean = false
)
