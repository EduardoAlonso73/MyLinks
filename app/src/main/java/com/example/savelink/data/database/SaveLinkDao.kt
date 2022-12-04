package com.example.savelink.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.savelink.data.entities.LinkEnt

@Dao
interface SaveLinkDao {
    @Query("SELECT * FROM SaveLinkEnt")
    fun getAllLinks():LiveData<MutableList<LinkEnt>>
    @Insert
    suspend fun savelink(saveLinkEnt: LinkEnt)
    @Update
    fun updatelink(saveLinkEnt: LinkEnt):Int
    @Delete
    fun deletelink(saveLinkEnt: LinkEnt):Int
}