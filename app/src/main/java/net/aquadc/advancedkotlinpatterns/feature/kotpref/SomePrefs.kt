package net.aquadc.advancedkotlinpatterns.feature.kotpref

import com.chibatching.kotpref.KotprefModel

object SomePrefs : KotprefModel() {

    val token by nullableStringPref()

}