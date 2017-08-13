package net.aquadc.advancedkotlinpatterns.feature.bind

import android.app.Fragment
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.view.View

const val ACTION_USER_UPDATED = "net.aquadc.advancedkotlinpatterns.feature.bind.ACTION_USER_UPDATED"

inline fun <T : View> T.bind(updateAction: String, crossinline bind: (T)->Unit) {
    addOnAttachStateChangeListener(object : LocalBroadcastBinder<T>(this@bind, updateAction) {
        override fun bind(view: T) = bind(view)
    })
}

fun Context.updated(updateAction: String) {
    LocalBroadcastManager.getInstance(this).sendBroadcast(Intent(updateAction))
}

@Suppress("NOTHING_TO_INLINE")
inline fun Fragment.updated(updateAction: String) = activity.updated(updateAction)

abstract class LocalBroadcastBinder<in T : View>(
        private val view: T,
        action: String
) : BroadcastReceiver(), View.OnAttachStateChangeListener {

    private val filter = IntentFilter(action)

    final override fun onViewAttachedToWindow(v: View) {
        LocalBroadcastManager.getInstance(v.context).registerReceiver(this, filter)
        bind(view)
    }

    final override fun onViewDetachedFromWindow(v: View) {
        LocalBroadcastManager.getInstance(v.context).unregisterReceiver(this)
    }

    final override fun onReceive(context: Context?, intent: Intent?) {
        bind(view)
    }

    protected abstract fun bind(view: T)

}