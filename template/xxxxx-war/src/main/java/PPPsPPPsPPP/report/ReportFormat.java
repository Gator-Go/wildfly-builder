package ppp.ppp.ppp.report;

import ppp.ppp.ppp.report.Constants;
import ppp.ppp.ppp.report.BaseException;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Lllll
 *
 * This is a class that represents a report output format.
 * It wraps an inner enum Type that defines the supported formats:
 * - PDF
 * - CSV
 * - HTML
 * - Excel
 * - DOCX (Word)
 * - RTF
 * - Plain text
 * Each enum value stores the MIME type and provides methods for the corresponding
 * file extension and a human-readable description.
 * The class itself offers:
 * - Constructors and a static factory (newInstance) to create a format from a MIME
 *   type string
 * - Getters for the type, name, extension, and MIME type
 * - A helper to convert a MIME type string into display text
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public class ReportFormat implements Serializable {

    private static final long serialVersionUID = 1008985123566767616L;
    private Type type = null;

    /**
     * Default no-arg constructor
     */
    public ReportFormat() {
    }

    /**
     * Constructor that allows for intialization of the report format
     * @param type the report format
     */
    public ReportFormat(ReportFormat.Type type) {
        this.type = type;
    }

    /**
     * Creates and initializes a new {@link ReportFormat} instance using the
     * mime type String passed in.
     * @param mimeType the String containing the mime type to be used to initialize
     * the new instance
     * @return ReportFormat object containing the information specified by the
     * mime type String
     * @throws BaseException if the new instance cannot be created and initialized
     * @throws IllegalArgumentException if any of the arguments passed in are
     * invalid
     */
    public static ReportFormat newInstance(String mimeType) throws IllegalArgumentException, BaseException {

        if (mimeType == null) {
            throw new IllegalArgumentException("A valid mime type string must be specified.");
        }
        ReportFormat.Type format = ReportFormat.Type.get(mimeType);
        if (format == null) {
            throw new BaseException("Unknown mime type " + mimeType);
        }
        return new ReportFormat(format);
    }

    /**
     * Returns the report format type
     * @return the report format type
     */
    public Type getType() {
        return this.type;
    }
    
    /**
     * Returns the report format name
     * @return the report format name
     */
    public String getName() {
        return this.type.getDescription();
    }

    /**
     * Returns the extension of the report format
     * @return the extension of the report format
     */
    public String getExtension() {
        return this.type.getExtension();
    }

    /**
     * Returns the mime type of the report format
     * @return the mime type of the report format
     */
    public String getMimeType() {
        return this.type.getValue();
    }

    /**
     * Returns the a human-readable version of the mime type
     * @param mimeType the mime type to convert to human-readable form
     * @return String containing the human-readable mime type
     */
    public static String getMimeDisplayText(String mimeType) {

        String text = null;
        try {
            ReportFormat.Type format = ReportFormat.Type.get(mimeType);
            text = format.getDescription();
        } catch (Exception ex) {
            // ignore this
        }
        return text;
    }

    /**
     * Enumeration defining the mime types available for each {@link ReportFormat}.
     */
    public enum Type {

        /** Enum defining the mime type for an Adobe PDF report. */
        MIME_TYPE_PDF(Constants.MIME_TYPE_PDF),
        /** Enum defining the mime type for a CSV (comma-separated values) report. */
        MIME_TYPE_CSV(Constants.MIME_TYPE_CSV),
        /** Enum defining the mime type for an HTML report. */
        MIME_TYPE_HTML(Constants.MIME_TYPE_HTML),
        /** Enum defining the mime type for a Microsoft Excel report. */
        MIME_TYPE_EXCEL(Constants.MIME_TYPE_EXCEL),
        /** Enum defining the mime type for a Microsoft Word report. */
        MIME_TYPE_DOCX(Constants.MIME_TYPE_DOCX),
        /** Enum defining the mime type for an RTF (rich-text format) report. */
        MIME_TYPE_RTF(Constants.MIME_TYPE_RTF),
        /** Enum defining the mime type for a plain text report. */
        MIME_TYPE_PLAINTEXT(Constants.MIME_TYPE_PLAINTEXT);

        private static final long serialVersionUID = 8237304909909422762L;

        private static final Map<String, Type> lookup
          = new HashMap<String, Type>();
        private final String value;

        /**
         * Static initializer to populate a map of the supported enumerations
         */
        static {
              for(Type s : EnumSet.allOf(Type.class))
                   lookup.put(s.getValue(), s);
         }

        /**
         * Constructor to create an initialize the enum
         * @param val the value corresponding to the enum to create
         */
        private Type(String val) {
            this.value = val;
        }

        /**
         * Returns the {@link Type} enumeration corresponding to the
         * code passed in
         * @param code {@link String} corresponding to the value for the enumeration
         * to retrieve
         * @return Type corresponding to the code passed in
         */
        public static Type get(String code) {
              return lookup.get(code);
        }

        /**
         * Returns the {@link String} equivalent for the {@link Type}
         * @return String value equivalent for the {@link Type}
         */
        public String getValue() {
            return this.value;
        }

        /**
         * Returns the file extension to use for the mime type.
         * @return String containing the file extension to use for the mime type
         */
        public String getExtension() {
            String extension = null;
            if (this.equals(MIME_TYPE_CSV)) {
                extension = Constants.MIME_TYPE_CSV_EXTENSION;
            } else if (this.equals(MIME_TYPE_HTML)) {
                extension = Constants.MIME_TYPE_HTML_EXTENSION;
            } else if (this.equals(MIME_TYPE_EXCEL)) {
                extension = Constants.MIME_TYPE_EXCEL_EXTENSION;
            } else if (this.equals(MIME_TYPE_DOCX)) {
                extension = Constants.MIME_TYPE_DOCX_EXTENSION;
            } else if (this.equals(MIME_TYPE_PDF)) {
                extension = Constants.MIME_TYPE_PDF_EXTENSION;
            } else if (this.equals(MIME_TYPE_RTF)) {
                extension = Constants.MIME_TYPE_RTF_EXTENSION;
            } else if (this.equals(MIME_TYPE_PLAINTEXT)) {
                extension = Constants.MIME_TYPE_PLAINTEXT_EXTENSION;
            }
            return extension;
        }

        /**
         * Returns a human-readable {@link String} for the mime type
         * @return String containing a human-readable {@link String} for the
         * mime type
         */
        public String getDescription() {
            String description = null;
            if (this.equals(MIME_TYPE_CSV)) {
                description = "Comma Delimited";
            } else if (this.equals(MIME_TYPE_HTML)) {
                description = "HTML";
            } else if (this.equals(MIME_TYPE_EXCEL)) {
                description = "Microsoft Excel";
            } else if (this.equals(MIME_TYPE_DOCX)) {
                description = "Microsoft Word";
            } else if (this.equals(MIME_TYPE_PDF)) {
                description = "PDF";
            } else if (this.equals(MIME_TYPE_RTF)) {
                description = "Rich Text Format";
            } else if (this.equals(MIME_TYPE_PLAINTEXT)) {
                description = "Text";
            }
            return description;
        }

    }

}
