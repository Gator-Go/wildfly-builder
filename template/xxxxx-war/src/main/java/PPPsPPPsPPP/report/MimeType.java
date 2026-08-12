package ppp.ppp.ppp.report;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Lllll
 *
 * This is a simple wrapper class containing a nested enum MimeTypeEnum that defines
 * supported report output file extensions.
 * The enum values are:
 * - PDF → "pdf"
 * - CSV → "csv"
 * - HTML → "html"
 * - EXCEL → "xls"
 * - WORD → "docx"
 * - RICH_TEXT → "rtf"
 * - TEXT → "txt"
 * It includes a static lookup map so an enum constant can be retrieved by its string
 * value (get(String code)), plus a getValue() method to retrieve the extension string.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public class MimeType
{

  public enum MimeTypeEnum {

    PDF("pdf"),
    CSV("csv"),
    HTML("html"),
    EXCEL("xls"),
    WORD("docx"),
    RICH_TEXT("rtf"),
    TEXT("txt");

    private static final long serialVersionUID = 847158488840604L;
    private final String value;
    private static final Map<String, MimeTypeEnum> lookup
          = new HashMap<String, MimeTypeEnum>();

    /**
     * Static initializer to populate a map of the supported enumerations
     */
    static {
          for(MimeTypeEnum s : EnumSet.allOf(MimeTypeEnum.class))
               lookup.put(s.getValue(), s);
     }

    /**
     * Constructor to create an initialize the enum
     * @param val the value corresponding to the enum to create
     */
    private MimeTypeEnum(String val) {
        this.value = val;
    }
    
    /**
     * Returns the {@link MimeTypeEnum} enumeration corresponding to the
     * code passed in
     * @param code {@link String} corresponding to the value for the enumeration
     * to retrieve
     * @return MimeStatusEnum corresponding to the code passed in
     */
    public static MimeTypeEnum get(String code) {
          return lookup.get(code);
     }


    /**
     * Returns the {@link String} equivalent for the {@link MimeTypeEnum}
     * @return String value equivalent for the {@link MimeTypeEnum}
     */
    public String getValue() {
        return this.value;
    }

  }
}
