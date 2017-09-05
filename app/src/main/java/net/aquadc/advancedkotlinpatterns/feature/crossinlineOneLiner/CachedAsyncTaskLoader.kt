package net.aquadc.advancedkotlinpatterns.feature.crossinlineOneLiner

import android.content.AsyncTaskLoader
import android.content.Context
import net.aquadc.advancedkotlinpatterns.feature.sealed.unions.either.Either
import net.aquadc.advancedkotlinpatterns.feature.sealed.unions.either.Left
import net.aquadc.advancedkotlinpatterns.feature.sealed.unions.either.Right

abstract class CachedLoader<D : Any>(
        context: Context
) : AsyncTaskLoader<Either<Throwable, D>>(context) {

    private var cached: Right<D>? = null

    final override fun onStartLoading() {
        val cached = cached
        if (cached == null) forceLoad()
        else deliverResult(cached)
    }

    final override fun loadInBackground(): Either<Throwable, D> = try {
        Right(load()).also { cached = it }
    } catch (e: Throwable) {
        Left(e)
    }

    @Throws(Throwable::class)
    abstract fun load(): D

}

inline fun <D : Any> CachedAsyncTaskLoader(
        context: Context, crossinline load: () -> D
) = object : CachedLoader<D>(context) {
    override fun load(): D = load.invoke()
}
