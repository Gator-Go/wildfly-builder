
package ppp.ppp.ppp.rest;

import ppp.ppp.ppp.event.msg.XxxxxAlertEvent;
import ppp.ppp.ppp.alert.XxxxxAlertListProducer;
import ppp.ppp.ppp.service.XxxxxAlertService;
import ppp.ppp.ppp.entity.XxxxxAlert;
import ppp.ppp.ppp.settings.XxxxxProperty;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.annotation.security.RolesAllowed;

/**
 * Lllll
 *
 * This is a JAX-RS REST service (AlertRestService) that exposes alert data as JSON
 * under the path /xxxxxAlerts.
 * It has two secured endpoints (roles ADMIN or USER):
 * - GET /xxxxxAlerts – Returns a list of all XxxxxAlertEvent objects obtained from
 *   XxxxxAlertListProducer.
 * - GET /xxxxxAlerts/{id} – Looks up a single alert by ID via XxxxxAlertService, maps
 *   the entity fields onto a new XxxxxAlertEvent (including formatting the occurredAt
 *   timestamp with the injected date-time pattern), and returns it.
 * The class is request-scoped and relies on CDI injection for its dependencies.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@Path("/xxxxxAlerts")
@RequestScoped
public class AlertRestService {

    @Inject
    private Logger log;

    @Inject
    @XxxxxProperty(name = "Format.DateTimeStr")
    private String FormatDateTimeStr;

    @Inject
    XxxxxAlertService xxxxxAlertService;

    @Inject
    XxxxxAlertListProducer xxxxxAlertListProducer;

    @RolesAllowed({"ADMIN","USER"})
    @GET
    @Produces("application/json")
    public List<XxxxxAlertEvent> listAllAlerts() {
        @SuppressWarnings("unchecked")

        final List<XxxxxAlertEvent> results = xxxxxAlertListProducer.getXxxxxAlerts();
        return results;
    }

    @RolesAllowed({"ADMIN","USER"})
    @GET
    @Path("/{id:-?[0-9][0-9]*}")
    @Produces("application/json")
    public XxxxxAlertEvent getAlertEvent(@PathParam("id") long id) {

	SimpleDateFormat formatDateTime = new SimpleDateFormat (FormatDateTimeStr);
        XxxxxAlert xxxxxAlert = xxxxxAlertService.getXxxxxAlert(new Long(id));
        XxxxxAlertEvent xxxxxAlertEvent = new XxxxxAlertEvent();

        xxxxxAlertEvent.setId(xxxxxAlert.getId());
        xxxxxAlertEvent.setDeviceId(xxxxxAlert.getDeviceId());
        xxxxxAlertEvent.setXxxxxAlertId(xxxxxAlert.getXxxxxAlertId());
        xxxxxAlertEvent.setXxxxxAlertType(xxxxxAlert.getXxxxxAlertType());
        xxxxxAlertEvent.setXxxxxAlertSource(xxxxxAlert.getXxxxxAlertSource());
        xxxxxAlertEvent.setXxxxxAlertMessage(xxxxxAlert.getXxxxxAlertMessage());
        xxxxxAlertEvent.setOccurredAt(formatDateTime.format(xxxxxAlert.getOccurredAt()));
        xxxxxAlertEvent.setName("xxxxxAlertEvent");

        return xxxxxAlertEvent;
    }
}
