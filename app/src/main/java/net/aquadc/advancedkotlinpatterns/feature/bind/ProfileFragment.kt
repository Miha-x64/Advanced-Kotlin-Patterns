package net.aquadc.advancedkotlinpatterns.feature.bind

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import org.jetbrains.anko.*
import java.util.*

class ProfileFragment : Fragment() {

    private lateinit var user: User
    private lateinit var emailInput: EditText
    private lateinit var nameInput: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?) = UI {

        user = savedInstanceState?.getParcelable("user") ?: User(UUID.randomUUID(), "", "")

        verticalLayout {
            layoutParams = FrameLayout.LayoutParams(matchParent, matchParent)
            padding = dip(16)

            textView {
                textSize = 20f
                padding = dip(8)
                bind(ACTION_USER_UPDATED) {
                    it.text = when {
                        user.name.isNotBlank() && user.email.isNotBlank() -> "${user.name} (${user.email})"
                        user.name.isNotBlank() -> user.name
                        user.email.isNotBlank() -> user.email
                        else -> user.id.toString()
                    }
                }
            }.lparams(matchParent, wrapContent)

            emailInput = editText {
                hint = "E-mail"
            }.lparams(matchParent, wrapContent)

            nameInput = editText {
                hint = "Name"
            }.lparams(matchParent, wrapContent)

            button("Save") {
                setOnClickListener { saveButtonClicked() }
            }
        }

    }.view

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("user", user)
    }

    private fun saveButtonClicked() {
        user.email = emailInput.text.toString()
        user.name = nameInput.text.toString()
        updated(ACTION_USER_UPDATED)
    }

}