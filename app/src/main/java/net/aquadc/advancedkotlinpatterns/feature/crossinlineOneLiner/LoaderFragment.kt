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
import android.widget.FrameLayout
import android.widget.TextView
import org.jetbrains.anko.*

class LoaderFragment : Fragment(), LoaderManager.LoaderCallbacks<CharSequence> {

    private lateinit var textView: TextView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loaderManager.initLoader(0, null, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?) = UI {

        textView = textView("Loading...") {
            layoutParams = FrameLayout.LayoutParams(matchParent, matchParent)
            padding = dip(16)
            textSize = 22f
            movementMethod = LinkMovementMethod.getInstance()
            setLineSpacing(0f, 1.2f)
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
        textView.text = data
    }

    override fun onLoaderReset(loader: Loader<CharSequence>) = Unit

}