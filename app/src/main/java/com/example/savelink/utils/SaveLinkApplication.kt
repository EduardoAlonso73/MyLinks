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

        val MIGRATION_LINK_DB_1_2=object:Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE  SaveLinkEnt  ADD COLUMN category TEXT NOT NULL DEFAULT '' ")
            }

        }
        val MIGRATION_LINK_DB_2_3=object:Migration(2,3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE  SaveLinkEnt  ADD COLUMN isSavaToBook INTEGER NOT NULL DEFAULT 0 ")
            }

        }

        database=Room.databaseBuilder(this,
            SaveLinkDatabase::class.java,
            "db_save_link").addMigrations(MIGRATION_LINK_DB_1_2,MIGRATION_LINK_DB_2_3).build()



        val MIGRATION_CATEGORY_DB_1_2=object:Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE  CategoryEnt  ADD COLUMN isChecked INTEGER NOT NULL DEFAULT 0 ")
            }

        }
        databaseCategory=Room.databaseBuilder(this,
        CategoryDatabase::class.java,"db_category").addMigrations(MIGRATION_CATEGORY_DB_1_2).build()
    }
//isChecked
}