package net.aquadc.advancedkotlinpatterns.common

import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import net.aquadc.advancedkotlinpatterns.MainActivity

fun FragmentManager.replaceAndCommit(newFragment: Fragment) {
    beginTransaction()
            .replace(MainActivity.FragmentContainerId, newFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
}
