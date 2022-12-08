package com.example.savelink.data.database.entities

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.savelink.data.entities.LinkEnt
import com.example.savelink.utils.SaveLinkApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveLinkRepository {
    private val accessDao by lazy { SaveLinkApplication.database.saveLinkDao() }

    val listAllLink: LiveData<MutableList<LinkEnt>> = liveData {
        val linkEnt = accessDao.getAllLinks()
        emitSource(linkEnt.map { it.sortedBy { it.id }.asReversed().toMutableList() })
    }

    val getFavoriteLink: LiveData<MutableList<LinkEnt>> = liveData {
        val linkEnt = accessDao.getAllFavoriteLinks()
        emitSource(linkEnt.map { it?.sortedBy { it.id }?.asReversed()?.toMutableList() ?: mutableListOf() })
    }



    suspend fun saveLink(link: LinkEnt) = withContext(Dispatchers.IO) {
        accessDao.savelink(link)
    }

    suspend fun updateLinkFavorite(link: LinkEnt) = withContext(Dispatchers.IO) {
        accessDao.updatelink(link)
    }

    suspend fun deleteLink(link: LinkEnt) = withContext(Dispatchers.IO) {
        accessDao.deletelink(link)
    }

}