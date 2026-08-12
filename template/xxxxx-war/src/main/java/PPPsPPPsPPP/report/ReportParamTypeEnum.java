package ppp.ppp.ppp.report;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Lllll
 *
 * This is an enum that defines the possible types of report parameters.
 * The values are:
 * - VALUE — a single value
 * - RANGE — a range of values
 * - SQL ("SELECT") — values come from a database SQL statement
 * - LIST — values come from a pipe-delimited list
 * It includes a static lookup map so an enum constant can be retrieved by its string value
 * (get(String code)), plus a getValue() method to retrieve the string representation.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public enum ReportParamTypeEnum {

    /** Enum to define a report parameter with a single value. */
    VALUE("VALUE"),
    /** Enum to define a report parameter that consists of a range of values. */
    RANGE("RANGE"),
    /** Enum to define a report parameter whose values are defined by a database SQL statement. */
    SQL("SELECT"),
    /** Enum to define a report parameter whose values are defined by a pipe-delimited list. */
    LIST("LIST");

    private static final long serialVersionUID = 5596646294795289175L;
    private final String value;
    private static final Map<String, ReportParamTypeEnum> lookup
          = new HashMap<String, ReportParamTypeEnum>();

    /**
     * Static initializer to populate a map of the supported enumerations
     */
    static {
          for(ReportParamTypeEnum s : EnumSet.allOf(ReportParamTypeEnum.class))
               lookup.put(s.getValue(), s);
     }

    /**
     * Constructor to create an initialize the enum
     * @param val the value corresponding to the enum to create
     */
    private ReportParamTypeEnum(String val) {
        this.value = val;
    }

    /**
     * Returns the {@link ReportParamTypeEnum} enumeration corresponding to the
     * code passed in
     * @param code {@link String} corresponding to the value for the enumeration
     * to retrieve
     * @return ReportStatusEnum corresponding to the code passed in
     */
    public static ReportParamTypeEnum get(String code) {
          return lookup.get(code);
     }

    /**
     * Returns the {@link String} equivalent for the {@link ReportTypeEnum}
     * @return String value equivalent for the {@link ReportTypeEnum}
     */
    public String getValue() {
        return this.value;
    }

}
