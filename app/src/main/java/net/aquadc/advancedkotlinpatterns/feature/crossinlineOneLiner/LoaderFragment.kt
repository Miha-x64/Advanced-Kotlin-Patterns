package net.aquadc.advancedkotlinpatterns.feature.crossinlineOneLiner

import android.app.Fragment
import android.app.LoaderManager
import android.content.Loader
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import org.jetbrains.anko.*

class LoaderFragment : Fragment(), LoaderManager.LoaderCallbacks<CharSequence> {

    private lateinit var loaderTextView: TextView
    private lateinit var watchedEditText: EditText
    private lateinit var watchedTextView: TextView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loaderManager.initLoader(0, null, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?) = UI {

        verticalLayout {
            layoutParams = FrameLayout.LayoutParams(matchParent, matchParent)
            padding = dip(16)

            loaderTextView = textView("Loading...") {
                textSize = 22f
                movementMethod = LinkMovementMethod.getInstance()
                setLineSpacing(0f, 1.2f)
            }.lparams(width = matchParent)

            watchedEditText = editText {
                id = 1
                hint = "Type anything..."
                addTextChangedListener(AfterTextChangedListener { watchedTextView.text = it })
            }.lparams(width = matchParent) {
                topMargin = dip(16)
            }

            watchedTextView = textView("I'm watching you!") {
                textSize = 14f
            }.lparams(width = matchParent) {
                topMargin = dip(16)
            }

        }

    }.view

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<CharSequence> = when (id) {
        0 -> CachedAsyncTaskLoader(activity) {
            Thread.sleep(3_000)
            SpannableStringBuilder("Content loaded! You may also ").apply {
                var start = length
                append("click here to see the power")
                setSpan(ClickableSpan { it.context.toast("See the power!") }, start, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                append(", or ")

                start = length
                append("tap here to feel the force")
                setSpan(ClickableSpan { it.context.vibrator.vibrate(500) }, start, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                append(".")
            }
        }
        else -> throw IndexOutOfBoundsException()
    }

    override fun onLoadFinished(loader: Loader<CharSequence>, data: CharSequence) {
        loaderTextView.text = data
    }

    override fun onLoaderReset(loader: Loader<CharSequence>) = Unit

}