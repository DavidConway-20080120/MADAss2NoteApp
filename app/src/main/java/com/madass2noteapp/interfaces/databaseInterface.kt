package com.madass2noteapp.interfaces

import com.madass2noteapp.dataClasses.Group
import com.madass2noteapp.dataClasses.Note
import com.madass2noteapp.mainApp.MainApp

interface databaseInterface {
    fun createGroup(group:Group)
    fun load()
    fun save()
    fun createNote(group: Group, note: Note){
        group.notes.add(note)
    }

    fun noteSwap(note:Note, from: Group, to:Group){
        from.notes.remove(note)
        to.notes.add(note)
    }

    fun getGroup(id:String?):Group?

    fun getNote(group:Group,id:String?):Note?{
       val found =  group.notes.find { p -> p.title == id }
        return found
    }

}