package com.example.savelink.utils

import com.example.savelink.data.entities.CategoryEnt

interface IOnCategorySelectListener {
    fun onSelectCategory(categoryEnt: CategoryEnt,position:Int)


}