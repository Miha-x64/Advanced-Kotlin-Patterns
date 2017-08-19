package net.aquadc.advancedkotlinpatterns.common

inline fun <T, reified R> Collection<T>.mapToArray(transform: (T) -> R): Array<R> {
    val itr = iterator()
    val array = Array(size) { transform(itr.next()) }
    check(!itr.hasNext())
    return array
}