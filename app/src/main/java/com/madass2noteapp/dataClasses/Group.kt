package com.madass2noteapp.dataClasses

data class Group constructor(var title:String = ""){
    var notes: ArrayList<Note> = arrayListOf<Note>()
}