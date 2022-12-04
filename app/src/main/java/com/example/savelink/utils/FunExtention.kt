package com.example.savelink.utils

import android.app.Activity
import android.widget.Toast

/*
fun Activity.previewUrl(url:String){
    this.setURL(itemLinks.link) { data ->
        pwUrl.title(data.title)
        pwUrl.description(data.description)
        pwUrl.host(data.host)
        pwUrl.thumbnail(data.thumbnailURL)
        pwUrl.favor(data.favorURL)
    }
}*/
fun Activity.toatMgs(mgs:String){
    Toast.makeText(this, mgs, Toast.LENGTH_SHORT).show()
}
