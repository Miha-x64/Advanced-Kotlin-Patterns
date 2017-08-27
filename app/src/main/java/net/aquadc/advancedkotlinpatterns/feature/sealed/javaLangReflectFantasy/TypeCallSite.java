package net.aquadc.advancedkotlinpatterns.feature.sealed.javaLangReflectFantasy;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

public class TypeCallSite {

    public static void main(String[] args) {
        getTypeSomewhere().visit(new Type.Visitor() {
            @Override
            public void visitClass(Class<?> klass) {

            }

            @Override
            public void visitParameterizedType(ParameterizedType parameterizedType) {

            }

            @Override
            public void visitGenericArrayType(GenericArrayType genericArrayType) {

            }

            @Override
            public void visitTypeVariable(TypeVariable<?> typeVariable) {

            }

            @Override
            public void visitWildcardType(WildcardType wildcardType) {

            }
        });
    }

    private static Type getTypeSomewhere() {
        throw new UnsupportedOperationException();
    }

}
