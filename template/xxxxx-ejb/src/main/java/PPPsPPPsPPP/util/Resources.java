
package ppp.ppp.ppp.util;

import java.util.logging.Logger;

import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Lllll
 *
 * This is a CDI producer bean (application-scoped) that supplies two injectable resources
 * in a Jakarta EE application: An EntityManager obtained from the persistence context
 * (via the @Produces + @PersistenceContext field) and A java.util.logging.Logger whose
 * name is the fully-qualified class name of whatever is injecting it (via the produceLog method).
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@ApplicationScoped
public class Resources {

    // use @SuppressWarnings to tell IDE to ignore warnings about field not being referenced directly
    @SuppressWarnings("unused")
    @Produces
    @PersistenceContext
    private EntityManager em;

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
