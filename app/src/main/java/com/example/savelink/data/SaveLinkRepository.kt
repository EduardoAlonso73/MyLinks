package com.example.savelink.data.database.entities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.savelink.data.entities.CategoryEnt
import com.example.savelink.data.entities.LinkEnt
import com.example.savelink.utils.SaveLinkApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveLinkRepository {
    private val accessDao by lazy { SaveLinkApplication.database.saveLinkDao() }
    private val accessCategoryDao by lazy { SaveLinkApplication.databaseCategory.categoryLink() }


    val listAllLink: LiveData<MutableList<LinkEnt>> = liveData {
        val linkEnt = accessDao.getAllLinks()
        emitSource(linkEnt.map { it.sortedBy { it.id }.asReversed().toMutableList() })
    }

    fun getLinkByCategory(categoryt: String): LiveData<MutableList<LinkEnt>> = liveData {
        val getLinkByCategory = accessDao.getLinkByCategory(categoryt)
        emitSource(getLinkByCategory.map { it.sortedBy { it.id}.asReversed().toMutableList() })
    }

    val getFavoriteLink: LiveData<MutableList<LinkEnt>> = liveData {
        val linkEnt = accessDao.getAllFavoriteLinks()
        emitSource(linkEnt.map {
            it?.sortedBy { it.id }?.asReversed()?.toMutableList() ?: mutableListOf()
        })
    }


    suspend fun saveLink(link: LinkEnt) = withContext(Dispatchers.IO) {
        accessDao.savelink(link)
    }

    suspend fun addCategory(category: CategoryEnt) = withContext(Dispatchers.IO) {
        accessCategoryDao.addCategoryLink(category)
    }

    suspend fun updateLinkFavorite(link: LinkEnt) = withContext(Dispatchers.IO) {
        accessDao.updatelink(link)
    }
    suspend fun updateLinkCategory(link: LinkEnt) = withContext(Dispatchers.IO) {
        accessDao.updatelink(link)
    }
    suspend fun updateCheckCategory(category: CategoryEnt) = withContext(Dispatchers.IO) {
        accessCategoryDao.updateCheckedCt(category)
    }

    suspend fun deleteLink(link: LinkEnt) = withContext(Dispatchers.IO) {
        accessDao.deletelink(link)
    }


    /*-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                        ---- Category ----
    -=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

    val listAllCategory: LiveData<MutableList<CategoryEnt>> = liveData {
        val categoryEnt = accessCategoryDao.getAllCategory()
        emitSource(categoryEnt.map { it.sortedBy { it.id }.asReversed().toMutableList() })
    }
 val listAllCategorySelect: LiveData<MutableList<CategoryEnt>> = liveData {
        val categoryEnt = accessCategoryDao.getAllCategory()
        emitSource(categoryEnt.map { it.sortedBy { it.id }.toMutableList() })
    }


}