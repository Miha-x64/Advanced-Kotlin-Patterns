package net.aquadc.advancedkotlinpatterns

import android.app.Fragment
import android.content.Context
import android.support.annotation.AttrRes
import android.util.TypedValue

private val intArray = intArrayOf(0)
fun Context.attr(@AttrRes attr: Int): TypedValue {
    val tv = TypedValue()
    val a = obtainStyledAttributes(tv.data, intArray.also { it[0] = attr })
    a.recycle()
    return tv
}

fun Fragment.attr(@AttrRes attr: Int) =
        activity.attr(attr)