package net.aquadc.advancedkotlinpatterns

import android.app.Activity
import android.os.Bundle

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(android.R.id.content, WelcomeFragment())
                    .commit()
        }
    }


}
