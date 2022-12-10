package com.example.savelink.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CategoryEnt")
data class CategoryEnt(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var category:String="")
