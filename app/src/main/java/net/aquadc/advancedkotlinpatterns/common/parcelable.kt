package net.aquadc.advancedkotlinpatterns.common

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import java.util.*

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

fun <E : Enum<E>> Bundle.putEnumSet(key: String, set: Set<E>) {
    putStringArray(key, set.mapToArray { it.name })
}

inline fun <reified E : Enum<E>> Bundle.getEnumSet(key: String): Set<E> {
    val names = getStringArray(key)
    val set = EnumSet.noneOf(E::class.java)
    names.forEach { set.add(enumValueOf(it)) }
    return set
}

inline fun <T, reified R> Collection<T>.mapToArray(transform: (T) -> R): Array<R> {
    val itr = iterator()
    val array = Array(size) { transform(itr.next()) }
    check(!itr.hasNext())
    return array
}