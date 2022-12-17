package com.example.savelink.damain

import androidx.lifecycle.LiveData
import com.example.savelink.data.database.entities.SaveLinkRepository
import com.example.savelink.data.entities.CategoryEnt
import com.example.savelink.data.entities.LinkEnt

class GetListCategoryCaseUsen {
    private val domainRepository by lazy { SaveLinkRepository()}
    fun getCategory(): LiveData<MutableList<CategoryEnt>> =domainRepository.listAllCategory
    fun getCategorySelect(): LiveData<MutableList<CategoryEnt>> =domainRepository.listAllCategorySelect
}