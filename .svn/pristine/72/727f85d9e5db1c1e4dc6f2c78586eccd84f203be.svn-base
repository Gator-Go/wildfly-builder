package ppp.ppp.ppp.settings;

import java.util.logging.Logger;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;

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