package net.aquadc.advancedkotlinpatterns.feature.crossinlineOneLiner

import android.content.AsyncTaskLoader
import android.content.Context

abstract class CachedLoader<D>(context: Context) : AsyncTaskLoader<D>(context) {

    private val cached: D? = null

    override fun onStartLoading() {
        val cached = cached
        if (cached == null) forceLoad()
        else deliverResult(cached)
    }

}

inline fun <D> CachedAsyncTaskLoader(
        context: Context, crossinline load: () -> D
) = object : CachedLoader<D>(context) {
    override fun loadInBackground(): D = load()
}