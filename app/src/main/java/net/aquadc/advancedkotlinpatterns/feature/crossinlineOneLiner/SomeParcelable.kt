package net.aquadc.advancedkotlinpatterns.feature.crossinlineOneLiner

import android.os.Parcel
import net.aquadc.advancedkotlinpatterns.common.BoringParcelable
import net.aquadc.advancedkotlinpatterns.common.parcelableCreator

class SomeParcelable(
        val value: String
): BoringParcelable {

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(value)
    }

    companion object {
        @JvmField val CREATOR =
                parcelableCreator { SomeParcelable(it.readString()) }
    }
}
