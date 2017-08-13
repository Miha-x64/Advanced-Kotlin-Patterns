package net.aquadc.advancedkotlinpatterns.feature.crossinlineOneLiner

import android.os.Parcel
import android.os.Parcelable

class SomeParcelable(
        val value: String
): Parcelable {

    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(value)
    }

    companion object {
        @JvmField val CREATOR =
                parcelableCreator { SomeParcelable(it.readString()) }
    }
}

inline fun <reified T> parcelableCreator(
        crossinline create: (Parcel)->T
) = object : Parcelable.Creator<T> {
    override fun newArray(size: Int): Array<T?> =
            arrayOfNulls(size)
    override fun createFromParcel(src: Parcel): T =
            create(src)
}