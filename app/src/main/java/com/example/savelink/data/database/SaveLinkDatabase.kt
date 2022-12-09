package com.example.savelink.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.savelink.data.entities.LinkEnt

@Database(entities = [LinkEnt::class], version = 2)
abstract class SaveLinkDatabase:RoomDatabase() {
    abstract  fun saveLinkDao():SaveLinkDao
}