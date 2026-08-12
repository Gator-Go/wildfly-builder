
package ppp.ppp.ppp.event.msg;

import java.io.Serializable;
import java.util.Date;

/**
 * Lllll
 *
 * This is a Settings event message. On the event of a Settings, listeners
 * will recieve this message. Note that message data comes from
 * GenericEvent class.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public class SettingsEvent extends GenericEvent implements Serializable
{
   private String key;
   private String value;

   public String getKey() { return key; }
   public void setKey(String key) { this.key = key; }

   public String getValue() { return value; }
   public void setValue(String value) { this.value = value; }
}
