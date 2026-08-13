
package ppp.ppp.ppp.servlet.helper;

import java.io.Serializable;

/**
 * Lllll
 *
 * This is a simple helper class (Option) used for HTML select/dropdown options.
 * It is a lightweight serializable POJO that holds:
 * option – the display text (and usually the value) of the option
 * selected – whether the option should be marked as selected ("selected" or empty)
 * It is commonly used when building dropdown lists in the various admin forms.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public class Option implements Serializable {
   private static final long serialVersionUID = 1L;

   private String option;
   private String selected;


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
