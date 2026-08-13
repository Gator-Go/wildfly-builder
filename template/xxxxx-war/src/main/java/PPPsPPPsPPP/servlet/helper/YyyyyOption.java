
package ppp.ppp.ppp.servlet.helper;

import java.io.Serializable;

/**
 * Lllll
 *
 * This is a simple helper class (YyyyyOption) used for HTML select/dropdown options
 * related to the Yyyyy entity.
 * It is used when building dropdown lists for Yyyyy-related fields.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public class YyyyyOption implements Serializable {
   private static final long serialVersionUID = 1L;

   private Long id;
   private String yyyyyOption;
   private String selected;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYyyyyOption() {
        return yyyyyOption;
    }

    public void setYyyyyOption(String yyyyyOption) {
        this.yyyyyOption = yyyyyOption;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

}
