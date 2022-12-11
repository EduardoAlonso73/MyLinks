package com.example.savelink.damain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.savelink.data.database.entities.SaveLinkRepository
import com.example.savelink.data.entities.CategoryEnt
import com.example.savelink.data.entities.LinkEnt

class GetListByCategoryCaseUsen {
    private val domainRepository by lazy { SaveLinkRepository()}

     fun getLinkByCategory(category:String):LiveData<MutableList<LinkEnt>> =domainRepository.getLinkByCategory(category)
}
