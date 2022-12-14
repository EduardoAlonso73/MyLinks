package com.example.savelink.damain

import com.example.savelink.data.database.entities.SaveLinkRepository
import com.example.savelink.data.entities.CategoryEnt
import com.example.savelink.data.entities.LinkEnt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddCategoryCaseUsen {
    private val domainRepository by lazy {  SaveLinkRepository()}

    suspend fun addCategory(category: CategoryEnt)= withContext(Dispatchers.IO){
        domainRepository.addCategory(category)
    }
}