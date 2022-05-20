package com.hossein.tamasha.utils

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun View.showInvisible(isShown:Boolean){
    if (isShown){
        this.visibility=View.VISIBLE
    }else{
        this.visibility=View.INVISIBLE
    }
}

fun RecyclerView.initRecyclerView(layoutManager: RecyclerView.LayoutManager,adapter : RecyclerView.Adapter<*>){
    this.layoutManager=layoutManager
    this.adapter=adapter
//    this.setHasFixedSize(true)
}