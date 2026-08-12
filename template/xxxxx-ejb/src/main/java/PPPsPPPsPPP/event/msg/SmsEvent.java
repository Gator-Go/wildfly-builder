
package ppp.ppp.ppp.event.msg;

import java.io.Serializable;

/**
 * Lllll
 *
 * This is a SMS event message. On the event of a SMS, listeners
 * will recieve this message. Note that the message data comes from
 * GenericEvent class.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public class SmsEvent extends GenericEvent implements Serializable {

   private String to;

    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }

}
