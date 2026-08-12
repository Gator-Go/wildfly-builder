
package ppp.ppp.ppp.event;

import ppp.ppp.ppp.event.msg.SmsEvent;

import ppp.ppp.ppp.event.msg.XxxxxAlertEvent;
import jakarta.enterprise.event.Event;
import ppp.ppp.ppp.settings.XxxxxProperty;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.context.Initialized;
import jakarta.inject.Inject;
import java.util.logging.Logger;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Lllll
 *
 * This is an application-scoped CDI bean that sends SMS messages via Twilio.
 * On application startup it reads Twilio credentials from system properties
 * and initializes the client (or disables SMS if they are missing). When an
 * SmsEvent is observed it sends the message, logs the result, fires an
 * XxxxxAlertEvent, and returns the Twilio message SID.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@ApplicationScoped
public class SmsListener {

    @Inject
    private Logger log;

    @Inject
    @XxxxxProperty(name = "Format.DateTimeStr")
    private String FormatDateTimeStr;

    @Inject
    private Event<XxxxxAlertEvent> alertEventMsg;

    private String accountSid;
    private String authToken;
    private String fromNumber;
    private String serviceSid;

    /**
     * This method runs exactly once when the Application context is initialized
     * (i.e. very early in application startup — perfect for Twilio.init)
     */
    void initialize(@Observes @Initialized(ApplicationScoped.class) Object ignored) {
    accountSid = System.getProperty("com.sw-builder.sync.app.twilio-sid");
    authToken = System.getProperty("com.sw-builder.sync.app.twilio-token");
    fromNumber = System.getProperty("com.sw-builder.sync.app.twilio-number");
    serviceSid = System.getProperty("com.sw-builder.sync.app.twilio-messaging-service-sid");

    if (accountSid == null || authToken == null || fromNumber == null || serviceSid == null) {
        log.warning("Twilio environment variables not set. SMS functionality disabled.");
        this.accountSid = null;  // mark as disabled
        return;
    }

    Twilio.init(accountSid, authToken);
    log.info("Twilio initialized successfully. Service Sid: " + serviceSid);
    }

    /**
     * event observer — will be called whenever someone fires SmsEvent
     */
    public String onSmsEvent(@Observes SmsEvent smsEvent) {
	if (accountSid == null) {
            log.warning("Twilio not configured - SMS skipped");
            return null;
	}
        log.info("Sending SMS event received: " + smsEvent.getName());

        try {
            Message message = Message.creator(
                    new PhoneNumber(smsEvent.getTo()),
                    new PhoneNumber(serviceSid),
                    smsEvent.getMessage())
                .create();

            String sid = message.getSid();

	    String msg = "SMS sent successfully → SID: " + sid;
            log.info(msg);
	    fireAlert("event", msg);

            return sid;   // return it so caller can log/track it if needed

        } catch (Exception e) {
	    String msg = "Failed to send SMS to " + smsEvent.getTo() + " → " + e.getMessage();
            log.warning(msg);
	    fireAlert("error", msg);

            return null;
        }
    }

    private void fireAlert(String alertType, String alertMessage) {
        
	SimpleDateFormat formatDateTime = new SimpleDateFormat (FormatDateTimeStr);
        XxxxxAlertEvent alertEvent = new XxxxxAlertEvent();
        alertEvent.setName("alertEvent");
        alertEvent.setXxxxxAlertType(alertType);
        alertEvent.setXxxxxAlertSource("SmsListener");
        alertEvent.setXxxxxAlertMessage(alertMessage);
        alertEvent.setOccurredAt(formatDateTime.format(new Date()));
        alertEventMsg.fire(alertEvent);
    }
}
