package net.aquadc.advancedkotlinpatterns.feature.sealed.javaReflectTypeFantasy;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

public interface Type {

    void visit(Visitor visitor);

    interface Visitor {
        void visitClass(Class<?> klass);
        void visitParameterizedType(ParameterizedType parameterizedType);
        void visitGenericArrayType(GenericArrayType genericArrayType);
        void visitTypeVariable(TypeVariable<?> typeVariable);
        void visitWildcardType(WildcardType wildcardType);
    }
}
