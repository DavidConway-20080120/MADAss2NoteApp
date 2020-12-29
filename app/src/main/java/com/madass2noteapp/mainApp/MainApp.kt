package com.madass2noteapp.mainApp

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.madass2noteapp.dataClasses.Group
import com.madass2noteapp.interfaces.databaseInterface
import java.io.File

class MainApp : Application(), databaseInterface{
    var allGroups = ArrayList<Group>()

    override fun onCreate() {
        super.onCreate()
        load()

    }

    override fun createGroup(group: Group) {
        allGroups.add(group)
    }

    override fun load() {
        try {
            val input: String? = File("/data/data/com.madass2noteapp/files/data.json").readText()
            if(input != null){// in file is found treads in and converts to array list
                val myType = object : TypeToken<ArrayList<Group>>() {}.type
                allGroups = Gson().fromJson(input, myType)
            }
        }
        catch (e: Exception) {
            val newGroup:Group = Group("Default")
            createGroup(newGroup)
        }
    }

    override fun save() {
        val saveData = Gson().toJson(allGroups)
        File("/data/data/com.madass2noteapp/files/data.json").writeText(saveData) //saves to data on close
    }

    override fun getGroup(id: String?): Group? {
        var found :Group? = allGroups.find { p -> p.title == id}
        return found
    }
}