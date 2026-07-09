
package ppp.ppp.ppp.servlet.helper;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class RptParamOption implements Serializable {
   private static final long serialVersionUID = 1L;

   private String name;
   private String instructStr;
   private String valueFlag;
   private String listFlag;
   private String selectFlag;
   private String dateFlag;
   private String dateFormat;
   private String dataType;
   private List<Option> listOptions = new ArrayList<Option>();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getInstructStr() {
        return instructStr;
    }
    public void setInstructStr(String instructStr) {
        this.instructStr = instructStr;
    }

    public String getValueFlag() {
        return valueFlag;
    }
    public void setValueFlag(String valueFlag) {
        this.valueFlag = valueFlag;
    }

    public String getListFlag() {
        return listFlag;
    }
    public void setListFlag(String listFlag) {
        this.listFlag = listFlag;
    }

    public String getSelectFlag() {
        return selectFlag;
    }
    public void setSelectFlag(String selectFlag) {
        this.selectFlag = selectFlag;
    }

    public String getDateFlag() {
        return dateFlag;
    }
    public void setDateFlag(String dateFlag) {
        this.dateFlag = dateFlag;
    }

    public String getDateFormat() {
        return dateFormat;
    }
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public List<Option> getListOptions() {
        return listOptions;
    }
    public void setListOptions(List<Option> listOptions) {
        this.listOptions = listOptions;
    }

}
