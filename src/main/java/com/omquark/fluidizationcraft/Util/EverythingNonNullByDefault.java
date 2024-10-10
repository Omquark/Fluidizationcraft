package com.omquark.fluidizationcraft.util;

import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.ParametersAreNullableByDefault;
import javax.annotation.meta.TypeQualifierDefault;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation can be applied to a package, class or method to indicate that
 * the method parameters are and the method returns nonnull by default unless there is:
 * <ul>
 * <li>An explicit nullness annotation
 * <li>The method overrides a method in a superclass (in which case the
 * annotation of the corresponding parameter in the superclass applies)
 * <li>There is a default parameter annotation (like {@link ParametersAreNullableByDefault})
 * applied to a more tightly nested element.
 * </ul>
 */

@NonNull
@TypeQualifierDefault({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EverythingNonNullByDefault {
}
