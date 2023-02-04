package me.lucaslah.javaconfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Config entry interface
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConfigEntry {
    /**
     * Hides an entry from the json file, essentially disabling it
     * @return true if it is to be hidden otherwise false
     */
    boolean isHidden() default false;
}
