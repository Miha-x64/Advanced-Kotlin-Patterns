package net.aquadc.advancedkotlinpatterns.feature.coroutines

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import kotlinx.coroutines.experimental.Deferred
import org.jetbrains.anko.*
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import ru.gildor.coroutines.retrofit.await
import java.util.concurrent.TimeUnit.SECONDS as Seconds // you can import `MILLISECONDS as Seconds` for testing ;)

class CoroutinesFragment : Fragment() {

    private lateinit var textView: TextView
    private var teaser: Deferred<Unit>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(i: LayoutInflater, c: ViewGroup, savedInstanceState: Bundle?): View = UI {
        verticalLayout {
            layoutParams = FrameLayout.LayoutParams(matchParent, wrapContent)
            padding = dip(16)

            button("Don't press me.") {
                setOnClickListener { loadButtonPressed() }
            }.lparams()

            textView = textView {
                id = 1
                freezesText = true
            }.lparams(width = matchParent) {
                horizontalMargin = dip(8)
                topMargin = dip(16)
            }

        }
    }.view

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (teaser != null) return
        teaser = async(UI) {
            textView.text = "Press the button."
            delay(3, Seconds)
            textView.text = "Hey. Press the button."
            delay(3, Seconds)
            textView.text = "Are you kidding me? Press it."
            delay(3, Seconds)
            textView.text = "Are you kidding me? Press it. Now."
            delay(3, Seconds)
            textView.text = "LOL, you're stubborn one."
            delay(3, Seconds)
            textView.text = "I can't wait. PRESS IT!"
            delay(3, Seconds)
            textView.text = "Okay, I will shut up. Please, press it."
            delay(5, Seconds)
            textView.text = "Ugh..."
            textView.text = try {
                val location = IpApi.myLocation.await()
                "I know, you're somewhere near to (${location.latitude}; ${location.longitude})! " +
                        "I'm on my way to ${location.city}, ${location.country}."
            } catch (e: Throwable) {
                "Oh, I'm even unable to figure out where do you live because of $e."
            }
        }
    }

    private fun loadButtonPressed() {
        teaser?.cancel()
        textView.text = "¯\\_(ツ)_/¯"
    }

    override fun onDestroy() {
        teaser?.cancel()
        super.onDestroy()
    }
}
