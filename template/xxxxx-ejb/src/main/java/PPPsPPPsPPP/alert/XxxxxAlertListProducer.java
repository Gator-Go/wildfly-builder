
package ppp.ppp.ppp.alert;

import ppp.ppp.ppp.entity.XxxxxAlert;
import ppp.ppp.ppp.event.msg.XxxxxAlertEvent;
import ppp.ppp.ppp.service.XxxxxAlertService;
import ppp.ppp.ppp.settings.XxxxxSettings;
import ppp.ppp.ppp.event.msg.SettingsChangedEvent;
import ppp.ppp.ppp.event.msg.AlertChangedEvent;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ejb.Startup;
import jakarta.ejb.Singleton;

/**
 * Lllll
 *
 * This is a CDI/EJB singleton that manages and caches a list of alerts for the UI.
 * On startup (@PostConstruct) and whenever settings or alerts change (via CDI events),
 * it loads the latest alerts from the service, converts them into alert objects
 * (with formatted timestamps), and exposes the list for the web page.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@Singleton
@Startup
@ApplicationScoped
public class XxxxxAlertListProducer {

    @Inject
    private Logger log;

    @Inject
    private XxxxxAlertService xxxxxAlertService;

    @Inject
    private XxxxxSettings xxxxxSettings;

    // xxxxx alerts for xxxxx alert web page
    private List<XxxxxAlertEvent> xxxxxAlertEvents;

    // xxxxx alert list access
    public List<XxxxxAlertEvent> getXxxxxAlerts() {
        return xxxxxAlertEvents;
    }

    public void onSettingsChanged(@Observes SettingsChangedEvent settingsChangedEvent) {
	retrieveXxxxxAlerts();
    }

    public void onAlertChanged(@Observes AlertChangedEvent alertChangedEvent) {
	retrieveXxxxxAlerts();
    }

    public void retrieveXxxxxAlerts() {

        SimpleDateFormat formatDateTime = new SimpleDateFormat (xxxxxSettings.getFormatDateTimeStr());
	xxxxxAlertEvents = new ArrayList();

        List<XxxxxAlert> xxxxxAlerts = xxxxxAlertService.getXxxxxAlertPage(0, xxxxxSettings.getRowsAlert().intValue());

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

         log.info("retrieveXxxxxAlerts finished with size of " + xxxxxAlerts.size());
    }


    @PostConstruct
    public void retrieveAlerts() {

        log.info("@PostConstruct:retrieveAlerts called");
	retrieveXxxxxAlerts();

    }
}
