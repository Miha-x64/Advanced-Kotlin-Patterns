package net.aquadc.advancedkotlinpatterns.feature.crossinlineOneLiner

import android.text.Editable
import android.text.TextWatcher

open class SimpleTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable) = Unit
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = Unit
}

inline fun AfterTextChangedListener(
        crossinline afterTextChanged: (s: Editable) -> Unit
) = object : SimpleTextWatcher() {
    override fun afterTextChanged(s: Editable) = afterTextChanged(s)
}