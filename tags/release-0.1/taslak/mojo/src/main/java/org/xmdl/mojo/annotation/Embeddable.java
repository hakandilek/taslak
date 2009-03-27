/**
 * 
 */
package org.xmdl.mojo.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Specifies an embeddable type.
 * 
 * @author Hakan Dilek
 */
@Target(ElementType.TYPE)
@Retention(RUNTIME)
public @interface Embeddable {

}
