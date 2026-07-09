
package ppp.ppp.ppp.servlet.helper;

import java.io.Serializable;

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
