package net.aquadc.advancedkotlinpatterns.feature.sealed.example

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.aquadc.advancedkotlinpatterns.app
import net.aquadc.advancedkotlinpatterns.common.recycler.BindingViewHolder
import net.aquadc.advancedkotlinpatterns.common.recycler.ListAdapter
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.UI
import org.jetbrains.anko.recyclerview.v7.recyclerView

class AttachmentsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View = UI {
        recyclerView {
            id = 1
            layoutManager = LinearLayoutManager(activity)
            adapter = ListAdapter(attachments,
                    this@AttachmentsFragment::getViewTypeOf,
                    this@AttachmentsFragment::createViewHolder)
        }
    }.view

    private fun getViewTypeOf(attachment: Attachment): Int = when (attachment) {
        is Link -> 1
        is Photo -> 2
        is Sticker -> 3
    }

    private fun createViewHolder(actx: AnkoContext<ListAdapter<Attachment>>, viewType: Int): BindingViewHolder<out Attachment> =
            when (viewType) {
                1 -> actx.createLinkHolder(app.picasso)
                2 -> actx.createPhotoHolder(app.picasso)
                3 -> actx.createStickerHolder(app.picasso)
                else -> throw IndexOutOfBoundsException()
            }

    private companion object {
        private val attachments = listOf(
                Link(
                        "https://developer.android.com/",
                        "Android Developers",
                        "Much useful information about bound services, content providers, AsyncTasks, SQLite cursors, or whatever you love.",
                        "https://pbs.twimg.com/profile_images/606585229034135553/2NqZJYQI.png"),
                Link(
                        "https://github.com/",
                        "GitHub",
                        "GitHub is a development platform inspired by the way you work.",
                        "https://assets-cdn.github.com/images/modules/logos_page/Octocat.png"
                ),
                Link(
                        "https://f-droid.org/",
                        "F-Droid",
                        "F-Droid is an installable catalogue of FOSS (Free and Open Source Software) applications for the Android platform.",
                        "http://img.over-blog-kiwi.com/0/71/05/10/20161028/ob_db889d_f-droid.png"
                ),
                Photo(
                        "I'll eat these as easily as I eat RAM, you know.",
                        "http://fscl01.fonpit.de/userfiles/6727621/image/2016/ANDROID-M-N-O-P/AndroidPIT-android-O-Oreo-2065.jpg"
                ),
                Photo(
                        "Nexus 5X (codenamed bullhead) is an Android smartphone manufactured by LG Electronics, co-developed with and marketed by Google Inc. as part of its Nexus line of flagship devices.",
                        "https://www.google.com/nexus/images/nexus5x/5x-gallery-thumb-01.jpg"
                ),
                Sticker(
                        "http://www.unixstickers.com/image/cache/data/stickers/android/android_shaped.sh-600x600.png"
                ),
                Sticker(
                        "http://devstickers.com/assets/img/pro/4mh6.png"
                ),
                Sticker(
                        "https://maxcdn.icons8.com/Share/icon/Operating_Systems//linux1600.png"
                )
        )
    }

}