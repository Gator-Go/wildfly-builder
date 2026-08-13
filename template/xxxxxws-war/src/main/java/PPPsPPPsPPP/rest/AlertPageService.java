
package ppp.ppp.ppp.rest;

import ppp.ppp.ppp.event.msg.XxxxxAlertEvent;
import ppp.ppp.ppp.service.XxxxxAlertService;
import ppp.ppp.ppp.entity.XxxxxAlert;
import ppp.ppp.ppp.settings.XxxxxProperty;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
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
 * This is a JAX-RS REST endpoint that provides paginated alert data as JSON.
 * Path: /xxxxxAlertPage/page/{pageStr}
 * Method: GET
 * Security: Restricted to users with the ADMIN or USER role
 * Scope: Request-scoped
 * It injects the XxxxxAlertService, a page-size setting (Rows.ToPage), and a date-time format.
 * For a given page number it:
 * - Calculates the offset
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@Path("/xxxxxAlertPage")
@RequestScoped
public class AlertPageService {

    @Inject
    private Logger log;

    @Inject
    @XxxxxProperty(name = "Rows.ToPage")
    private Integer RowsToPage;

    @Inject
    @XxxxxProperty(name = "Format.DateTimeStr")
    private String FormatDateTimeStr;

    @Inject
    XxxxxAlertService xxxxxAlertService;

    @RolesAllowed({"ADMIN","USER"})
    @GET
    @Path("page/{pageStr}")
    @Produces("application/json")
    public List<XxxxxAlertEvent> getAlertPage(@PathParam("pageStr") String pageStr) {
        @SuppressWarnings("unchecked")

	SimpleDateFormat formatDateTime = new SimpleDateFormat (FormatDateTimeStr);
        Long count = xxxxxAlertService.getCountXxxxxAlerts();

        int page = Integer.parseInt(pageStr);

        int rowsToPage = RowsToPage.intValue();

	int first = 0;
	if (page > 1)
            first = (page * rowsToPage) - (rowsToPage + 1);

        List<XxxxxAlert> xxxxxAlerts = xxxxxAlertService.getXxxxxAlertPage(first, rowsToPage);

        List<XxxxxAlertEvent> xxxxxAlertEvents = new ArrayList();
        for (XxxxxAlert xxxxxAlert : xxxxxAlerts) {

            XxxxxAlertEvent xxxxxAlertEvent = new XxxxxAlertEvent();

            xxxxxAlertEvent.setId(xxxxxAlert.getId());
            xxxxxAlertEvent.setDeviceId(xxxxxAlert.getDeviceId());
            xxxxxAlertEvent.setXxxxxAlertId(xxxxxAlert.getXxxxxAlertId());
            xxxxxAlertEvent.setXxxxxAlertType(xxxxxAlert.getXxxxxAlertType());
            xxxxxAlertEvent.setXxxxxAlertSource(xxxxxAlert.getXxxxxAlertSource());
            xxxxxAlertEvent.setXxxxxAlertMessage(xxxxxAlert.getXxxxxAlertMessage());
            xxxxxAlertEvent.setOccurredAt(formatDateTime.format(xxxxxAlert.getOccurredAt()));
            xxxxxAlertEvent.setName("xxxxxAlertEvent");

            xxxxxAlertEvents.add(xxxxxAlertEvent);
         }

        return xxxxxAlertEvents;
    }

}
