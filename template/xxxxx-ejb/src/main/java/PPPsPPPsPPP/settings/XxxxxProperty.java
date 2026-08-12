package ppp.ppp.ppp.settings;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import jakarta.enterprise.util.Nonbinding;
import jakarta.inject.Qualifier;

/**
 * Lllll
 *
 * This is a custom CDI qualifier annotation used to mark injection points
 * for configuration properties.
 * The non-binding name attribute specifies which property key should be injected
 * (looked up by the corresponding producer).
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER})
public @interface XxxxxProperty {

    @Nonbinding String name();
}