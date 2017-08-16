package net.aquadc.advancedkotlinpatterns.feature.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import org.jetbrains.anko.*

class SafeArgumentsAndModesFragment : Fragment {

    @Deprecated(message = "reflective use only", level = DeprecationLevel.ERROR)
    constructor()

    private constructor(args: Bundle) { arguments = args }

    private interface Mode {
        val label: CharSequence
        val userName: String
        val userSurname: String

        object Create : Mode {
            override val label: CharSequence get() = "Create new user"
            override val userName: String get() = ""
            override val userSurname: String get() = ""
        }

        class Edit(private val user: User) : Mode {
            override val label: CharSequence get() = "Edit user"
            override val userName: String get() = user.name
            override val userSurname: String get() = user.surname
        }
    }

    private val mode by lazy { when (arguments.getInt(KEY_MODE)) {
        MODE_CREATE -> Mode.Create
        MODE_EDIT -> Mode.Edit(arguments.getParcelable(KEY_USER))
        else -> throw IndexOutOfBoundsException()
    } }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) = UI {

        verticalLayout {
            layoutParams = FrameLayout.LayoutParams(matchParent, wrapContent)

            textView(mode.label)

            editText {
                hint = "Name"
                setText(mode.userName)
            }.lparams(width = matchParent)

            editText {
                hint = "Surname"
                setText(mode.userSurname)
            }.lparams(width = matchParent)
        }

    }.view

    companion object {
        private const val KEY_MODE = "mode"
        private const val MODE_CREATE = 0
        private const val MODE_EDIT = 1
        private const val KEY_USER = "user"

        fun createUser() = SafeArgumentsAndModesFragment(Bundle(1).apply {
            putInt(KEY_MODE, MODE_CREATE)
        })

        fun editUser(user: User) = SafeArgumentsAndModesFragment(Bundle(2).apply {
            putInt(KEY_MODE, MODE_EDIT)
            putParcelable(KEY_USER, user)
        })
    }

}