package com.example.savelink.utils

import android.app.Application
import androidx.room.Room
import com.example.savelink.data.database.SaveLinkDao
import com.example.savelink.data.database.SaveLinkDatabase

class SaveLinkApplication:Application() {
    companion object{
        lateinit var database:SaveLinkDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database=Room.databaseBuilder(this,
            SaveLinkDatabase::class.java,
            "db_save_link").build()
    }

}