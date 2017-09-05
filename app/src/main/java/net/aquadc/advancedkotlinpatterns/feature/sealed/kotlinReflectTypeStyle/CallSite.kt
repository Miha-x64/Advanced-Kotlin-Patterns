package net.aquadc.advancedkotlinpatterns.feature.sealed.kotlinReflectTypeStyle

fun main(args: Array<String>) {
    val type = getTypeSomewhere()
    println(when (type) {
        is Type.Class -> "class"
        is Type.ParameterizedType -> "parameterized type"
        is Type.GenericArrayType -> "generic array type"
        is Type.TypeVariable<*> -> "type variable"
        is Type.WildcardType -> "wildcard type"
    })
}

private fun getTypeSomewhere(): Type = throw UnsupportedOperationException()