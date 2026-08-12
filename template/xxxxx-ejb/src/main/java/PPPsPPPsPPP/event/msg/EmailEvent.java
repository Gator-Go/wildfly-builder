
package ppp.ppp.ppp.event.msg;

import java.io.Serializable;

/**
 * Lllll
 *
 * This is an email event message. On the event of an email, listeners
 * will recieve this message. Note that the message data comes from
 * GenericEvent class.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public class EmailEvent extends GenericEvent implements Serializable {

   private String to;
   private String cc;
   private String bcc;
   private String subject;

    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }


    public String getCc() {
        return cc;
    }
    public void setCc(String cc) {
        this.cc = cc;
    }


    public String getBcc() {
        return bcc;
    }
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }


    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

}
