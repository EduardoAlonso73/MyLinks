package com.example.savelink.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.savelink.data.entities.LinkEnt

@Dao
interface SaveLinkDao {
    @Query("SELECT * from SaveLinkEnt ")
     fun getAllLinks():LiveData<MutableList<LinkEnt>>

    @Query("SELECT * FROM SaveLinkEnt WHERE isFavorite=1")
    fun getAllFavoriteLinks():LiveData<MutableList<LinkEnt>?>
    @Insert
    suspend fun savelink(saveLinkEnt: LinkEnt)
    @Update
    fun updatelink(saveLinkEnt: LinkEnt):Int
    @Delete
    fun deletelink(saveLinkEnt: LinkEnt):Int

    @Query("SELECT * FROM SaveLinkEnt WHERE category=:category")
     fun getLinkByCategory(category:String):LiveData<MutableList<LinkEnt>>

}