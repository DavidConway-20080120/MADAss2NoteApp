package com.madass2noteapp.mainApp

import android.app.Application

class MainApp : Application(){
    val test = ArrayList<String>()

    override fun onCreate() {
        super.onCreate()
        test.add("test1")
        test.add("test2")
        test.add("test3")

    }
}