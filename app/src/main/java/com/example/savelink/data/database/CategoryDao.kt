package com.example.savelink.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.savelink.data.entities.CategoryEnt
import com.example.savelink.data.entities.LinkEnt


@Dao
interface CategoryDao {
    @Insert
    suspend fun addCategoryLink(category: CategoryEnt)
    @Query("SELECT * FROM CategoryEnt")
    fun getAllCategory(): LiveData<MutableList<CategoryEnt>>

    @Update
    fun updateCheckedCt(category: CategoryEnt):Int
}