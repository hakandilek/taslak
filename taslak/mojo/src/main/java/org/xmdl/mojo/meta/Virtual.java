/**
 * 
 */
package org.xmdl.mojo.meta;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Specifies virtual type.
 * 
 * @author Hakan Dilek
 */
@Target(ElementType.TYPE)
@Retention(RUNTIME)
public @interface Virtual {

}
