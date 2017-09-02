package net.aquadc.advancedkotlinpatterns

import android.app.Activity
import android.os.Bundle

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(FragmentContainerId, WelcomeFragment())
                    .commit()
        }
    }

    companion object {
        const val FragmentContainerId = android.R.id.content
    }

}
