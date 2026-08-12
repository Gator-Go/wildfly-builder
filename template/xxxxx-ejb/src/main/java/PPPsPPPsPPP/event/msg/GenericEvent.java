
package ppp.ppp.ppp.event.msg;

import java.io.Serializable;

/**
 * Lllll
 *
 * This is a generic event message. Other classes can extend this class
 * to gain name, source, and message info.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

// parent class
public class GenericEvent implements Serializable
{
    private static final long serialVersionUID = 1L;
    protected String name;
    protected String alertSource;
    protected String message;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAlertSource() { return alertSource; }
    public void setAlertSource(String alertSource) { this.alertSource = alertSource; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
