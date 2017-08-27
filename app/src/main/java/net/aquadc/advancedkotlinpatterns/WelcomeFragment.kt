package net.aquadc.advancedkotlinpatterns

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import net.aquadc.advancedkotlinpatterns.common.replaceAndCommit
import net.aquadc.advancedkotlinpatterns.feature.ankoRecyclerView.AnkoRecyclerViewFragment
import net.aquadc.advancedkotlinpatterns.feature.bind.ProfileFragment
import net.aquadc.advancedkotlinpatterns.feature.coroutines.CoroutinesFragment
import net.aquadc.advancedkotlinpatterns.feature.crossinlineOneLiner.LoaderFragment
import net.aquadc.advancedkotlinpatterns.feature.fragments.safe.FoodFilterAndSortChooserFragment
import net.aquadc.advancedkotlinpatterns.feature.fragments.safe.FoodListFragment
import net.aquadc.advancedkotlinpatterns.feature.sealed.example.AttachmentsFragment
import org.jetbrains.anko.UI
import org.jetbrains.anko.listView
import org.jetbrains.anko.matchParent

class WelcomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View = UI {
        listView {
            layoutParams = FrameLayout.LayoutParams(matchParent, matchParent)

            val itemAdapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, listOf(
                    Item("Food list (RecyclerView with Anko)", ::AnkoRecyclerViewFragment),
                    Item("Edit user (Reactive binding)", ::ProfileFragment),
                    Item("Food by popularity (safe fragment)", { FoodListFragment(FoodListFragment.Mode.Popular) }),
                    Item("Food filter (safe fragment)", ::FoodFilterAndSortChooserFragment),
                    Item("A fragment with CachedAsyncTaskLoader, ClickableSpan, and AfterTextChangedListener (crossinline)", ::LoaderFragment),
                    Item("Coroutines", ::CoroutinesFragment),
                    Item("Sealed classes (Attachments adapter)", ::AttachmentsFragment)
            ))
            adapter = itemAdapter

            setOnItemClickListener { _, _, position, _ ->
                fragmentManager
                        .replaceAndCommit(itemAdapter.getItem(position).createFragment())
            }
        }
    }.view

    private class Item(
            private val label: String,
            val createFragment: () -> Fragment
    ) {
        override fun toString() = label
    }

}