package net.aquadc.advancedkotlinpatterns.feature.bind

import android.os.Parcel
import android.os.Parcelable
import net.aquadc.advancedkotlinpatterns.common.parcelableCreator
import java.util.*

class User(
        val id: UUID,
        var email: String,
        var name: String
) : Parcelable {

    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id.mostSignificantBits)
        dest.writeLong(id.leastSignificantBits)
        dest.writeString(email)
        dest.writeString(name)
    }

    companion object {
        @JvmField val CREATOR = parcelableCreator {
            User(
                    id = UUID(it.readLong(), it.readLong()),
                    email = it.readString(),
                    name = it.readString()
            )
        }
    }

}