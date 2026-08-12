package ppp.ppp.ppp.report;

import ppp.ppp.ppp.report.BaseException;
import ppp.ppp.ppp.report.StringUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

/**
 * Lllll
 *
 * This is a data class (POJO) that represents a parameter for a report.
 * It stores:
 * - name, description, dataType, and type
 * - Whether the parameter is required
 * - Optional minValue / maxValue
 * - listOptions (collection of allowed values, also supported as a pipe-delimited string)
 * - An optional sqlStatement (for dynamic lists)
 * - An optional dateFormat
 * It also provides convenience methods:
 * - isDateType() — checks if the parameter is a Date or Timestamp
 * - isBoolean() — checks if the parameter is a Boolean
 * - Helpers to convert list options to/from a pipe-delimited string
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public class ReportParameter implements Serializable {

    private static final long serialVersionUID = -1054678210841574197L;
    private String name = null;
    private String description = null;
    private boolean required = false;
    private String dataType = null;
    private String type = null;
    private Object minValue = null;
    private Object maxValue = null;
    private Collection<String> listOptions = null;
    private String sqlStatement = null;
    private String dateFormat = null;

    /**
     * Returns the parameter name
     * @return the parameter name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the parameter
     * @param name the new name of the parameter
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the parameter
     * @return the description of the parameter
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the parameter
     * @param description the new description of the parameter
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Returns a flag true/false if the parameter is required
     * @return true if the parameter is required, otherwise false
     */
    public boolean isRequired() {
        return this.required;
    }

    /**
     * Sets the required flag for the parameter
     * @param required the new required flag
     */
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * Returns the data type for the parameter
     * @return the data type for the parameter
     */
    public String getDataType() {
        return this.dataType;
    }

    /**
     * Sets the data type for the parameter
     * @param dataType the data type for the parameter
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * Returns the type for the parameter
     * @return the type for the parameter
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets the type for the parameter
     * @param type the type for the parameter
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the min value for the parameter
     * @param min the min value for the parameter
     */
    public void setMinValue(Object min) {
        this.minValue = min;
    }

    /**
     * Returns the min value for the parameter
     * @return Object containing the min value for the parameter
     */
    public Object getMinValue() {
        return this.minValue;
    }

    /**
     * Sets the max value for the parameter
     * @param max the max value for the parameter
     */
    public void setMaxValue(Object max) {
        this.maxValue = max;
    }

    /**
     * Returns the max value for the parameter
     * @return Object containing the max value for the parameter
     */
    public Object getMaxValue() {
        return this.maxValue;
    }

    /**
     * Sets the SQL statement for the parameter
     * @param sqlStatement SQL statement for the parameter
     */
    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement;
    }

    /**
     * Returns the SQL statement for the parameter
     * @return String containing the SQL statement for the parameter
     */
    public String getSqlStatement() {
        return sqlStatement;
    }

    /**
     * Sets the date format for the parameter
     * @param dateFormat the date format for the parameter
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * Returns the date format for the parameter
     * @return String containing the date format for the parameter
     */
    public String getDateFormat() {
        return this.dateFormat;
    }

    /**
     * Sets the list options for the parameter
     * @param listOptions the list options for the parameter
     */
    public void setListOptions(Collection<String> listOptions) {
        this.listOptions = listOptions;
    }

    /**
     * Returns the list options for the parameter
     * @return Collection containing the list options for the parameter
     */
    public Collection<String> getListOptions() {
        return this.listOptions;
    }

    /**
     * Sets the list options for the parameter by parsing the pipe-delimited
     * String passed in and populating the internal member Collection for the class.
     * @param listOptions pipe-delimited String containing the list options
     */
    public void setListOptionsString(String listOptions) {
        if (StringUtils.isNotBlank(listOptions)) {
            if (this.listOptions == null) {
                this.listOptions = new ArrayList<String>();
            } else {
                this.listOptions.clear();
            }
            String[] listOptionsArr = listOptions.split("\\|");
            for (String listOption : listOptionsArr) {
                this.listOptions.add(listOption);
            }
        }
    }

    /**
     * Returns a String of the pipe-delimited list options
     * @return String containing the pipe-delimited list options
     */
    public String getListOptionsString() {
        String result = null;
        if ((this.listOptions != null) && (!this.listOptions.isEmpty())) {
            result = StringUtils.join(this.listOptions.toArray(new String[]{}), "|");
        }
        return result;
    }

    /**
     * Returns a boolean indicating whether or not the data type for the parameter
     * is either {@link java.util.Date} or {@link java.sql.Timestamp}.
     * @return boolean true if the parameter is a date type and false otherwise
     */
    public boolean isDateType() {
        if (StringUtils.isBlank(this.dataType)) {
            return false;
        } else {
            return ((this.dataType.equals(Date.class.getCanonicalName())) ||
                (this.dataType.equals(Timestamp.class.getCanonicalName())));
        }
    }

    /**
     * Returns a boolean indicating whether or not the data type for the report
     * parameter is {@link java.lang.Boolean}.
     * @return boolean true if the parameter is {@link java.lang.Boolean} and
     * false otherwise
     */
    public boolean isBoolean() {
        if (StringUtils.isBlank(this.dataType)) {
            return false;
        } else {
            return ((this.dataType.equals(Boolean.class.getCanonicalName())));
        }
    }



}
