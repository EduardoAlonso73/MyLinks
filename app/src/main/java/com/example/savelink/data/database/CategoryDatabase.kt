package com.example.savelink.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.savelink.data.entities.CategoryEnt

@Database(entities = [CategoryEnt::class], version = 2)
abstract class CategoryDatabase:RoomDatabase() {
    abstract  fun categoryLink():CategoryDao
}