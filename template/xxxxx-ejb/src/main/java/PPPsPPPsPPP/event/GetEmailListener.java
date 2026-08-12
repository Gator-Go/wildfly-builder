
package ppp.ppp.ppp.event;

import ppp.ppp.ppp.event.msg.GetEmailEvent;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.logging.Logger;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

/**
 * Lllll
 *
 * This is a startup singleton / application-scoped CDI bean that listens
 * for GetEmailEvent and retrieves messages from a Gmail inbox via IMAP.
 * It connects using an injected mail session and system-property credentials,
 * opens the INBOX read-only, logs the message count and subjects, then cleanly
 * closes the folder and store.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@Singleton
@Startup
@ApplicationScoped
public class GetEmailListener {

    @Inject
    private Logger log;

    @Resource(name = "java:jboss/mail/gmail-incoming")
    private Session sessionReceive;

    private String gmailEmail = System.getProperty("com.sw-builder.sync.app.gmail-email");
    private String gmailPassword = System.getProperty("com.sw-builder.sync.app.gmail-password");

  public void onGetEmailEvent(@Observes GetEmailEvent getEmailEvent) {
    Store store = null;
    Folder folder = null;
    try {
        store = sessionReceive.getStore("imaps");

        // Only connect if not already connected
        if (!store.isConnected()) {
            store.connect("imap.gmail.com", gmailEmail, gmailPassword);
        }

        folder = store.getFolder("INBOX");
        if (!folder.isOpen()) {
            folder.open(Folder.READ_ONLY);
        }

        Message[] messages = folder.getMessages();
        log.info("Successfully connected! Found " + messages.length + " messages in INBOX");

        for (Message message : messages) {
            log.info("Subject: " + message.getSubject());
            // You can add more: message.getFrom(), message.getSentDate(), etc.
        }

    } catch (MessagingException ex) {
        log.warning("Problem getting email from Gmail: " + ex.getMessage());
        ex.printStackTrace();
    } finally {
        // Proper cleanup
        try {
            if (folder != null && folder.isOpen()) {
                folder.close(false);
            }
            if (store != null && store.isConnected()) {
                store.close();
            }
        } catch (Exception ignored) {}
    }
  }
}