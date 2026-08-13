
package ppp.ppp.ppp.servlet.helper;

import java.io.Serializable;

/**
 * Lllll
 *
 * This is a simple helper class (OptionId) used for HTML select/dropdown options that need both
 * an ID and a display value.
 * It is a lightweight serializable POJO that holds:
 * id – the underlying value (usually a database ID)
 * option – the display text shown to the user
 * selected – whether the option should be marked as selected
 * It is typically used when building dropdown lists that must submit an ID while showing
 * a human-readable name.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public class OptionId implements Serializable {
   private static final long serialVersionUID = 1L;

   private String id;
   private String option;
   private String selected;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

}
