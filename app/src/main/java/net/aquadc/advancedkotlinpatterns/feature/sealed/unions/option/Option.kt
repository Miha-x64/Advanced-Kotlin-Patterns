package net.aquadc.advancedkotlinpatterns.feature.sealed.unions.option

/**
 * Represents Scala-style optional type Option
 */
sealed class Option<out T : Any>

class Some<out T : Any>(
        val value: T
) : Option<T>() {
    override fun hashCode(): Int = value.hashCode() + 1
    override fun equals(other: Any?): Boolean = other is Some<*> && value == other.value
    override fun toString(): String = "Some($value)"
    operator fun component1() = value
}

object None : Option<Nothing>() {
    override fun hashCode(): Int = 1
    override fun equals(other: Any?): Boolean = other === this
    override fun toString(): String = "None"
}

inline fun <T : Any> Option<T>.ifPresent(consumer: (T) -> Unit) = when (this) {
    is Some -> consumer(value)
    None -> Unit
}

inline fun <T : Any> Option<T>.filter(predicate: (T) -> Boolean) = when (this) {
    is Some -> if (predicate(value)) this else None
    None -> None
}

inline fun <T : Any, R : Any> Option<T>.map(transform: (T) -> R) = when (this) {
    is Some -> Some(transform(value))
    None -> None
}

fun <T : Any, R : Any> Option<T>.map(transform: Option<(T) -> R>) = when {
    this is Some && transform is Some -> Some(transform.value(value))
    else -> None
}

inline fun <T : Any, R : Any> Option<T>.flatMap(transform: (T) -> Option<R>) = when (this) {
    is Some -> transform(value)
    None -> None
}

fun <T : Any> Option<T>.orElse(fallback: T) = when (this) {
    is Some -> value
    None -> fallback
}

fun <T : Any> Option<T>.orElse(lazyFallback: () -> T) = when (this) {
    is Some -> value
    None -> lazyFallback()
}
