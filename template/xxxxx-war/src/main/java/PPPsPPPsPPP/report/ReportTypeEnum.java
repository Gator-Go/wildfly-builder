package ppp.ppp.ppp.report;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public enum ReportTypeEnum {

    /** Enum to describe Jasper reports. */
    JASPER("JASPER");

    private static final long serialVersionUID = 2440847158488840604L;
    private final String value;
    private static final Map<String, ReportTypeEnum> lookup
          = new HashMap<String, ReportTypeEnum>();

    /**
     * Static initializer to populate a map of the supported enumerations
     */
    static {
          for(ReportTypeEnum s : EnumSet.allOf(ReportTypeEnum.class))
               lookup.put(s.getValue(), s);
     }

    /**
     * Constructor to create an initialize the enum
     * @param val the value corresponding to the enum to create
     */
    private ReportTypeEnum(String val) {
        this.value = val;
    }
    
    /**
     * Returns the {@link ReportTypeEnum} enumeration corresponding to the
     * code passed in
     * @param code {@link String} corresponding to the value for the enumeration
     * to retrieve
     * @return ReportStatusEnum corresponding to the code passed in
     */
    public static ReportTypeEnum get(String code) {
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
