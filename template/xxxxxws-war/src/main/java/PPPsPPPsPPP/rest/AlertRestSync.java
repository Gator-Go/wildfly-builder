
package ppp.ppp.ppp.rest;

import ppp.ppp.ppp.entity.XxxxxAlert;
import ppp.ppp.ppp.service.XxxxxAlertService;

import ppp.ppp.ppp.event.msg.XxxxxAlertEvent;
import jakarta.enterprise.event.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.ParseException;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the xxxxx alert table.
 */
@Path("/syncXxxxxAlerts")
@RequestScoped
public class AlertRestSync {


    @Inject
    XxxxxAlertService xxxxxAlertService;

    @Inject
    private Event<XxxxxAlertEvent> xxxxxAlertEventMsg;

    private JSONObject jsonObject;
    private JSONArray jsonArray;

    @GET
    @Path("/{param}")
    @Produces("application/json")
    public Response printMessage(@PathParam("param") String syncAlerts) {

        String result = "Success";

        JSONArray jsonArray = new JSONArray(syncAlerts);

        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);

            int deviceIdInt = jsonObject.getInt("deviceId");
            Long deviceId = new Long(deviceIdInt);
            String xxxxxAlertType = jsonObject.getString("xxxxxAlertType");
            String xxxxxAlertSource = jsonObject.getString("xxxxxAlertSource");
            String xxxxxAlertMessage = jsonObject.getString("xxxxxAlertMessage");
            String occurredAtStr = jsonObject.getString("occurredAt");
            Long occurredAtLong = new Long(occurredAtStr);
            Date occurredAt = new Date(occurredAtLong.longValue());

            XxxxxAlert xxxxxAlert = new XxxxxAlert();

            xxxxxAlert.setDeviceId(deviceId);
            xxxxxAlert.setXxxxxAlertType(xxxxxAlertType);
            xxxxxAlert.setXxxxxAlertSource(xxxxxAlertSource);
            xxxxxAlert.setXxxxxAlertMessage(xxxxxAlertMessage);
            xxxxxAlert.setOccurredAt(occurredAt);

            xxxxxAlertService.addXxxxxAlert(xxxxxAlert);
        }
        return Response.status(200).entity(result).build();
    }
}
