package com.madass2noteapp.dataClasses

data class Group constructor(val title:String){
    val notes: ArrayList<Note> = arrayListOf<Note>()
}