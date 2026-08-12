package ppp.ppp.ppp.settings;

import java.util.logging.Logger;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;

/**
 * Lllll
 *
 * This is a CDI producer class that supplies configuration property values
 * (as String, Integer, or Boolean) based on a custom @XxxxxProperty qualifier.
 * It looks up the property name from the annotation on the injection point,
 * retrieves the value from an injected XxxxxSettings instance, and converts
 * it to the requested type (returning null if missing). A logger is used to
 * report missing/empty properties.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public class XxxxxProducer {

    @Inject
    private Logger log;

    @Inject
    private XxxxxSettings xxxxxSettings;


    @Produces
    @XxxxxProperty(name = "")
    public String stringProperty(InjectionPoint injectionPoint) {
        
        String propertyName = injectionPoint.getAnnotated().getAnnotation(XxxxxProperty.class).name();
        String value = xxxxxSettings.getProperty(propertyName);
        
        if (value == null || propertyName.trim().length() == 0) {
            log.info("No property found with name " + value);
        }
        return value;
    }
    
    @Produces
    @XxxxxProperty(name = "")
    public Integer integerProperty(InjectionPoint injectionPoint) {
        
        String value = stringProperty(injectionPoint);
        return value == null ? null : new Integer(value);
    }

    @Produces
    @XxxxxProperty(name = "")
    public Boolean booleanProperty(InjectionPoint injectionPoint) {
        
        String value = stringProperty(injectionPoint);
        return value == null ? null : new Boolean(value);
    }

}