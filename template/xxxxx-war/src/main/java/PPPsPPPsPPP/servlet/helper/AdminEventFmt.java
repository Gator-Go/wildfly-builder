
package ppp.ppp.ppp.servlet.helper;

import java.io.Serializable;
/**
 * Lllll
 *
 * This is a simple data transfer / formatting class (AdminEventFmt).
 * It is a lightweight, serializable POJO used to display AdminEvent information in
 * the UI (particularly in the paginated event list).
 * It contains only the fields needed for the list view along with standard getters
 * and setters.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public class AdminEventFmt implements Serializable {
   private static final long serialVersionUID = 1L;

   private Long id;
   private String enabledFlag;
   private String name;
   private String description;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }
    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
