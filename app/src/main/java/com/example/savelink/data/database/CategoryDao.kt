package com.example.savelink.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.savelink.data.entities.CategoryEnt



@Dao
interface CategoryDao {
    @Insert
    suspend fun addCategoryLink(category: CategoryEnt)
    @Query("SELECT * FROM CategoryEnt")
    fun getAllCategory(): LiveData<MutableList<CategoryEnt>>
}