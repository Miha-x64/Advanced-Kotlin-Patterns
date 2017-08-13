package net.aquadc.advancedkotlinpatterns

import android.app.Fragment
import android.app.FragmentTransaction
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import net.aquadc.advancedkotlinpatterns.feature.ankoRecyclerView.AnkoRecyclerViewFragment
import org.jetbrains.anko.UI
import org.jetbrains.anko.listView
import org.jetbrains.anko.matchParent

class WelcomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View = UI {
        listView {
            layoutParams = FrameLayout.LayoutParams(matchParent, matchParent)

            val itemAdapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, listOf(
                    Item("RecyclerView with Anko", ::AnkoRecyclerViewFragment)
            ))
            adapter = itemAdapter

            setOnItemClickListener { _, _, position, _ ->
                fragmentManager
                        .beginTransaction()
                        .replace(android.R.id.content, itemAdapter.getItem(position).createFragment())
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
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