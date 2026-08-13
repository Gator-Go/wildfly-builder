
package ppp.ppp.ppp.servlet.helper;
import java.io.Serializable;

/**
 * Lllll
 *
 * This is a simple formatting / transfer class (ParameterFmt).
 * It is a lightweight serializable POJO used to display and manage event parameters
 * on the Admin Event forms (add, edit, and fire) along with standard getters and setters.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public class ParameterFmt implements Serializable {
   private static final long serialVersionUID = 1L;

   private String deleteFlag;
   private String id;
   private String required;
   private String name;
   private String description;
   private String fireValue;

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

    public String getRequired() {
        return required;
    }
    public void setRequired(String required) {
        this.required = required;
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

    public String getFireValue() {
        return fireValue;
    }
    public void setFireValue(String fireValue) {
        this.fireValue = fireValue;
    }

}
