package com.madass2noteapp.dataClasses

import android.os.Parcel
import android.os.Parcelable

data class Group(var title: String? = ""):Parcelable{
    var notes: ArrayList<Note> = arrayListOf<Note>()

    constructor(parcel: Parcel) : this(parcel.readString()) {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Group> {
        override fun createFromParcel(parcel: Parcel): Group {
            return Group(parcel)
        }

        override fun newArray(size: Int): Array<Group?> {
            return arrayOfNulls(size)
        }
    }
}