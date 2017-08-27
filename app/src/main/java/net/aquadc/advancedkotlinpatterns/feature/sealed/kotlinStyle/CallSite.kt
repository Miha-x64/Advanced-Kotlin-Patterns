package net.aquadc.advancedkotlinpatterns.feature.sealed.kotlinStyle

fun main(args: Array<String>) {
    val type = getTypeSomewhere()
    when (type) {
        is Type.Class -> { }
        is Type.ParameterizedType -> { }
        is Type.GenericArrayType -> { }
        is Type.TypeVariable<*> -> { }
        is Type.WildcardType -> { }
    }!!
}

private fun getTypeSomewhere(): Type = throw UnsupportedOperationException()