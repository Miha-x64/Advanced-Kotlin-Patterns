package net.aquadc.advancedkotlinpatterns.feature.sealed.kotlinReflectTypeStyle

import java.lang.reflect.GenericDeclaration

sealed class Type {

    class Class : Type()
    class ParameterizedType : Type()
    class GenericArrayType : Type()
    class TypeVariable<D : GenericDeclaration> : Type()
    class WildcardType : Type()

}