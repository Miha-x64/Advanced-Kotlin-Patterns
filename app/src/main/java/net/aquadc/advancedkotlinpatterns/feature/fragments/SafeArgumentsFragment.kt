package net.aquadc.advancedkotlinpatterns.feature.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import org.jetbrains.anko.*

class SafeArgumentsFragment : Fragment {

    @Deprecated(message = "reflective use only", level = DeprecationLevel.ERROR)
    constructor()

    private constructor(args: Bundle) { arguments = args }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) = UI {

        val mode = arguments.getInt(KEY_MODE)

        verticalLayout {
            layoutParams = FrameLayout.LayoutParams(matchParent, wrapContent)

            textView(when (mode) {
                MODE_CREATE -> "Create new user"
                MODE_EDIT -> "Edit user"
                else -> throw IndexOutOfBoundsException()
            })

            editText {
                hint = "Name"
                if (mode == MODE_EDIT) setText(arguments.getParcelable<User>(KEY_USER).name)
            }.lparams(width = matchParent)

            editText {
                hint = "Surname"
                if (mode == MODE_EDIT) setText(arguments.getParcelable<User>(KEY_USER).surname)
            }.lparams(width = matchParent)
        }

    }.view

    companion object {
        private const val KEY_MODE = "mode"
        private const val MODE_CREATE = 0
        private const val MODE_EDIT = 1
        private const val KEY_USER = "user"

        fun createUser() = SafeArgumentsFragment(Bundle(1).apply {
            putInt(KEY_MODE, MODE_CREATE)
        })

        fun editUser(user: User) = SafeArgumentsFragment(Bundle(2).apply {
            putInt(KEY_MODE, MODE_EDIT)
            putParcelable(KEY_USER, user)
        })
    }

}