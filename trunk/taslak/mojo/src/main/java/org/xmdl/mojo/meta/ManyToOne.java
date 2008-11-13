/**
 * 
 */
package org.xmdl.mojo.meta;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Specifies one-to-many relationship.
 * 
 * @author Hakan Dilek
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface ManyToOne {

	String target() default "";
	
}
