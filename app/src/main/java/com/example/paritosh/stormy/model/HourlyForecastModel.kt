package com.example.paritosh.stormy.model

import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.DrawableRes

class HourlyForecastModel : Parcelable {
    @DrawableRes
    var icon: Int = 0
    var time: String? = null
    var summary: String? = null
    var temperature: Double = 0.toDouble()

    constructor()

    constructor(`in`: Parcel) {
        icon = `in`.readInt()
        time = `in`.readString()
        summary = `in`.readString()
        temperature = `in`.readDouble()
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(icon)
        dest.writeString(time)
        dest.writeString(summary)
        dest.writeDouble(temperature)
    }

    companion object CREATOR : Parcelable.Creator<HourlyForecastModel> {
        override fun createFromParcel(parcel: Parcel): HourlyForecastModel {
            return HourlyForecastModel(parcel)
        }

        override fun newArray(size: Int): Array<HourlyForecastModel?> {
            return arrayOfNulls(size)
        }
    }
}
