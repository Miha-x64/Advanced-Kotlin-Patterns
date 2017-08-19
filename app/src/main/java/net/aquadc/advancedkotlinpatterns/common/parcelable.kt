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

fun <E : Enum<E>> Parcel.writeEnumSet(set: Set<E>) {
    writeStringArray(set.mapToArray { it.name })
}

inline fun <reified E : Enum<E>> Parcel.readEnumSet(): Set<E> {
    val names = createStringArray()
    val set = EnumSet.noneOf(E::class.java)
    names.forEach { set.add(enumValueOf(it)) }
    return set
}

fun Parcel.writeBoolean(value: Boolean) {
    writeByte(if (value) 1 else 0)
}

fun Parcel.readBoolean(): Boolean {
    val value = readByte()
    return when (value) {
        0.toByte() -> false
        1.toByte() -> true
        else -> throw IllegalArgumentException("Cannot read byte value $value as Boolean, 0 or 1 expected.")
    }
}

fun Parcel.writeEnum(value: Enum<*>) {
    writeString(value.name)
}

inline fun <reified E : Enum<E>> Parcel.readEnum(): E {
    return enumValueOf(readString())
}