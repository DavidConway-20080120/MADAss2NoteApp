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

    }

    override fun createGroup(group: Group) {
        allGroups.add(group)
    }

    override fun getGroup(id: String?): Group? {
        var found :Group? = allGroups.find { p -> p.title == id}
        return found
    }
}