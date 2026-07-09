
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
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the xxxxx alerts.
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
