package com.example.savelink.utils

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.savelink.data.database.CategoryDatabase
import com.example.savelink.data.database.SaveLinkDao
import com.example.savelink.data.database.SaveLinkDatabase
import java.util.Objects

class SaveLinkApplication:Application() {
    companion object{
        lateinit var database:SaveLinkDatabase
        lateinit var databaseCategory: CategoryDatabase
    }

    override fun onCreate() {
        super.onCreate()

        val MIGRATION_1_2=object:Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE  SaveLinkEnt  ADD COLUMN category TEXT NOT NULL DEFAULT '' ")
            }

        }

        database=Room.databaseBuilder(this,
            SaveLinkDatabase::class.java,
            "db_save_link").addMigrations(MIGRATION_1_2).build()

        
        databaseCategory=Room.databaseBuilder(this,
        CategoryDatabase::class.java,"db_category").build()
    }

}