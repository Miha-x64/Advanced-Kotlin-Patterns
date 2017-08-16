package net.aquadc.advancedkotlinpatterns.feature.fragments

import android.os.Parcel
import android.os.Parcelable
import net.aquadc.advancedkotlinpatterns.feature.crossinlineOneLiner.parcelableCreator
import java.util.*

class User(
        val id: UUID,
        val name: String,
        val surname: String
) : Parcelable {

    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id.mostSignificantBits)
        dest.writeLong(id.leastSignificantBits)
        dest.writeString(name)
        dest.writeString(surname)
    }

    companion object {
        @JvmField val CREATOR = parcelableCreator { User(
                id = UUID(it.readLong(), it.readLong()),
                name = it.readString(),
                surname = it.readString()
        ) }
    }

}