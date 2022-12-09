package com.example.savelink.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.savelink.data.entities.CategoryEnt
import com.example.savelink.data.entities.LinkEnt

@Database(entities = [CategoryEnt::class], version = 1)
abstract class CategoryDatabase:RoomDatabase() {
    abstract  fun saveLinkDao():CategoryDao
}