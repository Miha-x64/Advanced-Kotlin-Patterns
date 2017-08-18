package net.aquadc.advancedkotlinpatterns.common

import android.os.Parcel
import android.os.Parcelable

inline fun <reified T> parcelableCreator(
        crossinline create: (Parcel)->T
) = object : Parcelable.Creator<T> {
    override fun newArray(size: Int): Array<T?> =
            arrayOfNulls(size)
    override fun createFromParcel(src: Parcel): T =
            create(src)
}

interface BoringParcelable : Parcelable {
    override fun describeContents(): Int = 0
}