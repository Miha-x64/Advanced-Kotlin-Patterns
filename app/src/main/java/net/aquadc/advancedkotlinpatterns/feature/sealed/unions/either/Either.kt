package net.aquadc.advancedkotlinpatterns.feature.sealed.unions.either

sealed class Either<out L : Any, out R : Any>

class Left<out T : Any>(
        val value: T
) : Either<T, Nothing>() {
    override fun hashCode(): Int = 3 * value.hashCode()
    override fun equals(other: Any?): Boolean = other is Left<*> && value == other.value
    override fun toString(): String = "Left($value)"
}

class Right<out T : Any>(
        val value: T
): Either<Nothing, T>() {
    override fun hashCode(): Int = 7 * value.hashCode()
    override fun equals(other: Any?): Boolean = other is Right<*> && value == other.value
    override fun toString(): String = "Right($value)"
}

inline fun <L : Any, R : Any, RR> Either<L, R>.let(transformLeft: (L) -> RR, transformRight: (R) -> RR) = when (this) {
    is Left -> transformLeft(value)
    is Right -> transformRight(value)
}

inline fun <L : Any, R : Any, TL : Any> Either<L, R>.mapLeft(transform: (L) -> TL) = when (this) {
    is Left -> Left(transform(value))
    is Right -> this
}

inline fun <L : Any, R : Any, TR : Any> Either<L, R>.mapRight(transform: (R) -> TR) = when (this) {
    is Left -> this
    is Right -> Right(transform(value))
}

inline fun <L : Any, R : Any, TL : Any, TR : Any> Either<L, R>.map(
        transformLeft: (L) -> TL, transformRight: (R) -> TR
) = when (this) {
    is Left -> Left(transformLeft(value))
    is Right -> Right(transformRight(value))
}

fun <L : Any> Either<L, *>.leftOrElse(fallback: L) = when (this) {
    is Left -> value
    is Right -> fallback
}

inline fun <L : Any> Either<L, *>.leftOrElse(lazyFallback: () -> L) = when (this) {
    is Left -> value
    is Right -> lazyFallback()
}

fun <R : Any> Either<*, R>.rightOrElse(fallback: R) = when (this) {
    is Left -> fallback
    is Right -> value
}

inline fun <R : Any> Either<*, R>.rightOrElse(lazyFallback: () -> R) = when (this) {
    is Left -> lazyFallback()
    is Right -> value
}
