package ppp.ppp.ppp.rest;

import ppp.ppp.ppp.entity.XxxxxAlert;
import ppp.ppp.ppp.service.XxxxxAlertService;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Date;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Lllll
 *
 * REST endpoint for synchronizing Xxxxx alerts.
 * Accepts a JSON array of alerts via POST and persists them.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */
@Path("/syncXxxxxAlerts")
@RequestScoped
public class AlertRestSync {

    @Inject
    XxxxxAlertService xxxxxAlertService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response syncAlerts(String syncAlertsJson) {
        try {
            JSONArray jsonArray = new JSONArray(syncAlertsJson);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Long deviceId = jsonObject.getLong("deviceId");
                String xxxxxAlertType = jsonObject.getString("xxxxxAlertType");
                String xxxxxAlertSource = jsonObject.getString("xxxxxAlertSource");
                String xxxxxAlertMessage = jsonObject.getString("xxxxxAlertMessage");
                long occurredAtMillis = jsonObject.getLong("occurredAt");
                Date occurredAt = new Date(occurredAtMillis);

                XxxxxAlert xxxxxAlert = new XxxxxAlert();
                xxxxxAlert.setDeviceId(deviceId);
                xxxxxAlert.setXxxxxAlertType(xxxxxAlertType);
                xxxxxAlert.setXxxxxAlertSource(xxxxxAlertSource);
                xxxxxAlert.setXxxxxAlertMessage(xxxxxAlertMessage);
                xxxxxAlert.setOccurredAt(occurredAt);

                xxxxxAlertService.addXxxxxAlert(xxxxxAlert);
            }

            return Response.ok("{\"status\":\"Success\"}").build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"status\":\"Error\",\"message\":\"" + e.getMessage() + "\"}")
                           .build();
        }
    }
}