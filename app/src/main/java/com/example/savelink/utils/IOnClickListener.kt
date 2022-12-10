package com.example.savelink.utils

import com.example.savelink.data.entities.LinkEnt

interface IOnClickListener {
    fun onClick(linkEnt: LinkEnt)
    fun isFavorite(linkEnt: LinkEnt)
    fun removeLink(linkEnt: LinkEnt)
    fun shareLink(link: String)
    fun copiClipboard(link: String)
    fun bookAdA(linkEnt: LinkEnt)
}