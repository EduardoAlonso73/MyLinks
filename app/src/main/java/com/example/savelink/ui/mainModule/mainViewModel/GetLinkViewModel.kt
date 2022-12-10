package com.example.savelink.ui.mainModule.mainViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savelink.damain.*
import com.example.savelink.data.entities.CategoryEnt
import com.example.savelink.data.entities.LinkEnt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetLinkViewModel : ViewModel() {

    private val getListCategoCaseUse by lazy { GetListCategoryCaseUsen() }
    private val getListByCategoCaseUse by lazy { GetListByCategoryCaseUsen() }
    private val getListLinkCaseUse by lazy { GetListLinkCaseUsen() }
    private val updateFavorite by lazy { UpdateFavorite() }
    private val deleteLink by lazy { DeleteLink() }
    var getLink=MutableLiveData<MutableList<LinkEnt>>() //🚀 👀


      fun loadListLink(){
        viewModelScope.launch {
            getLink.postValue(getListLinkCaseUse.getLink())
        }
    }

    fun getListByCategory(category: String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            val categotList=getListByCategoCaseUse.getLinkByCategory(category)
            getLink.postValue(categotList)
        }

    }


    fun getListCategory(): LiveData<MutableList<CategoryEnt>> = getListCategoCaseUse.getCategory()


    fun updateLinkFavorite(linkEnt: LinkEnt) {
        linkEnt.isFavorite = !linkEnt.isFavorite
        viewModelScope.launch(Dispatchers.IO) {
            updateFavorite.updateLinkFavorite(linkEnt)
        }

    }

    fun deleteLink(linkEnt: LinkEnt) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteLink.deleteLink(linkEnt)
        }

    }



    // var getListLink:LiveData<MutableList<LinkEnt>> = getListLink()

    /* var getListLink: MutableLiveData<MutableList<LinkEnt>> = getListLink() as MutableLiveData<MutableList<LinkEnt>>

     fun getListByCatego()
     {
         getListLink.postValue(getLinkByCatego() as MutableList<LinkEnt>)

     }
 */
    //TODO: Acceder al metodo selectByCategory()

    /*   private fun getListLink(): LiveData<MutableList<LinkEnt>> {
           return getListLinkCaseUse.getLink()
       }*/




    /*  private fun getLinkByCatego(): LiveData<MutableList<LinkEnt>> {
          return getListByCategoCaseUse.getLinkByCategory("Medium")
      }*/

}