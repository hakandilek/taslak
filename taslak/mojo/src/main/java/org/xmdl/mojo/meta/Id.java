/**
 * 
 */
package org.xmdl.mojo.meta;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Specifies the primary key property or field of an entity.
 * 
 * @author Hakan Dilek
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Id {

}
