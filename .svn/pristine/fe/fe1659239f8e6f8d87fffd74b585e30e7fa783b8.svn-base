package ppp.ppp.ppp.report;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public enum DatePickerEnum {

    DATE_FORMAT("yy/oo"),
    DATE_TIME_FORMAT("yy/oo 00:00");

    private static final long serialVersionUID = 5596646294795289175L;
    private final String value;
    private static final Map<String, DatePickerEnum> lookup
          = new HashMap<String, DatePickerEnum>();

    /**
     * Static initializer to populate a map of the supported enumerations
     */
    static {
          for(DatePickerEnum s : EnumSet.allOf(DatePickerEnum.class))
               lookup.put(s.getValue(), s);
     }

    /**
     * Constructor to create an initialize the enum
     * @param val the value corresponding to the enum to create
     */
    private DatePickerEnum(String val) {
        this.value = val;
    }

    /**
     * Returns the {@link DatePickerEnum} enumeration corresponding to the
     * code passed in
     * @param code {@link String} corresponding to the value for the enumeration
     * to retrieve
     * @return ReportStatusEnum corresponding to the code passed in
     */
    public static DatePickerEnum get(String code) {
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
