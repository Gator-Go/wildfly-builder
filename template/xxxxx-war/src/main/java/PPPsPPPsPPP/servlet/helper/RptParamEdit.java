
package ppp.ppp.ppp.servlet.helper;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Lllll
 *
 * This is a formatting / transfer class used for editing report parameters (RptParamEdit).
 * It is a serializable POJO that holds all the data needed to display and edit a single
 * RptParam on the Admin Report Edit form along with standard getters and setters.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public class RptParamEdit implements Serializable {
   private static final long serialVersionUID = 1L;
   private String paramId;
   private String name;
   private String desc;
   private String req;
   private String dataType;
   private String paramType;
   private String minValue;
   private String maxValue;
   private String listOptions;
   private String sqlStmt;
   private String dateFormat;
   private List<Option> paramOptions = new ArrayList<Option>();
   private List<Option> datePickerOptions = new ArrayList<Option>();

    public String getParamId() {
        return paramId;
    }
    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getReq() {
        return req;
    }
    public void setReq(String req) {
        this.req = req;
    }

    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getParamType() {
        return paramType;
    }
    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getMinValue() {
        return minValue;
    }
    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getMaxValue() {
        return maxValue;
    }
    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getListOptions() {
        return listOptions;
    }
    public void setListOptions(String listOptions) {
        this.listOptions = listOptions;
    }

    public String getSqlStmt() {
        return sqlStmt;
    }
    public void setSqlStmt(String sqlStmt) {
        this.sqlStmt = sqlStmt;
    }

    public String getDateFormat() {
        return dateFormat;
    }
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public List<Option> getParamOptions() {
        return paramOptions;
    }
    public void setParamOptions(List<Option> paramOptions) {
        this.paramOptions = paramOptions;
    }

    public List<Option> getDatePickerOptions() {
        return datePickerOptions;
    }
    public void setDatePickerOptions(List<Option> datePickerOptions) {
        this.datePickerOptions = datePickerOptions;
    }

}
