package com.example.projectintents.models

import android.os.Parcel
import android.os.Parcelable

data class MyProfile(
    val myName: String,
    val myAge: Int,
    val myEmail: String,
    val myPhoneNumber: String,
    val myImage: String
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(myName)
        parcel.writeInt(myAge)
        parcel.writeString(myEmail)
        parcel.writeString(myPhoneNumber)
        parcel.writeString(myImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyProfile> {
        override fun createFromParcel(parcel: Parcel): MyProfile {
            return MyProfile(parcel)
        }

        override fun newArray(size: Int): Array<MyProfile?> {
            return arrayOfNulls(size)
        }
    }

}
