
package ppp.ppp.ppp.servlet.helper;

import java.io.Serializable;

/**
 * Lllll
 *
 * It is a lightweight serializable POJO used to display and manage the list
 * of selected events on the SMS Subscription add/edit forms along with standard
 * getters and setters.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public class SmsSubsFmt implements Serializable {
   private static final long serialVersionUID = 1L;

   private String deleteFlag;
   private String id;
   private String name;
   private String description;

    public String getDeleteFlag() {
        return deleteFlag;
    }
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
