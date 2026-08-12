package ppp.ppp.ppp.report;

/**
 * Lllll
 *
 * This is a utility class that holds a collection of public static final constants
 * (it has a private constructor so it cannot be instantiated).
 * It defines values used across the report/messaging framework, including:
 * - File buffer size
 * - HTTP methods (GET, POST, PUT, DELETE)
 * - MIME types and file extensions (XML, HTML, PDF, Excel, DOCX, RTF, CSV, ZIP, images, JSON, SOAP, etc.)
 * - Common HTTP headers
 * - Transport names (HTTP, JMS, FILE, DIRECT)
 * - Web-service related constants (namespaces, service types)
 * - Miscellaneous values (localhost, logging subsystem name, comment field size, etc.)
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public final class Constants
{

    /** 
     * private default no-arg constructor
     */
    private Constants()
    {
    }
    
    // -------------------------------------------------------------------------
    //  MESSAGE ROUTER + HTTP CONSTANTS
    // -------------------------------------------------------------------------
    
    /** An integer representing the buffer size for file input and output. */
    public static final int FILE_BUFFER_SIZE = 2048;
    /** Constant for HTTP GET verb. */
    public static final String HTTP_METHOD_GET = "GET";
    /** Constant for HTTP POST verb. */
    public static final String HTTP_METHOD_POST = "POST";
    /** Constant for HTTP PUT verb. */
    public static final String HTTP_METHOD_PUT = "PUT";
    /** Constant for HTTP DELETE verb. */
    public static final String HTTP_METHOD_DELETE = "DELETE";
    /** Constant for soap mime type. */
    public static final String MIME_TYPE_SOAP = "application/soap+xml";
    /** Constant for xml mime type. */
    public static final String MIME_TYPE_XML = "text/xml";
    /** Constant for xml mime type extension. */
    public static final String MIME_TYPE_XML_EXTENSION = "xml";
    /** Constant for html mime type. */
    public static final String MIME_TYPE_HTML = "text/html";
    /** Constant for html mime type extension. */
    public static final String MIME_TYPE_HTML_EXTENSION = "html";
    /** Constant for plain mime type. */
    public static final String MIME_TYPE_PLAINTEXT = "text/plain";
    /** Constant for plain mime type extension. */
    public static final String MIME_TYPE_PLAINTEXT_EXTENSION = "txt";
    /** Constant for css mime type. */
    public static final String MIME_TYPE_CSS = "text/css";
    /** Constant for ODT mime type. */
    public static final String MIME_TYPE_ODT = "application/vnd.oasis.opendocument.text";
    /** Constant for json type. */
    public static final String MIME_TYPE_JSON = "application/json";
    /** Constant for png image type. */
    public static final String MIME_TYPE_PNG = "image/png";
    /** Constant for jpeg image type. */
    public static final String MIME_TYPE_JPEG = "image/jpeg";
    /** Constant for gif image type. */
    public static final String MIME_TYPE_GIF = "image/gif";
    /** Constant for pdf mime type. */
    public static final String MIME_TYPE_PDF = "application/pdf";
    /** Constant for pdf mime type extension. */
    public static final String MIME_TYPE_PDF_EXTENSION = "pdf";
    /** Constant for excel mime type. */
    public static final String MIME_TYPE_EXCEL = "application/excel";
    /** Constant for excel mime type extension. */
    public static final String MIME_TYPE_EXCEL_EXTENSION = "xls";
    /** Constant for word mime type. */
    public static final String MIME_TYPE_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    /** Constant for word mime type extension. */
    public static final String MIME_TYPE_DOCX_EXTENSION = "docx";
    /** Constant for rtf mime type. */
    public static final String MIME_TYPE_RTF = "application/rtf";
    /** Constant for rtf mime type extension. */
    public static final String MIME_TYPE_RTF_EXTENSION = "rtf";
    /** Constant for csv mime type. */
    public static final String MIME_TYPE_CSV = "text/csv";
    /** Constant for csv mime type extension. */
    public static final String MIME_TYPE_CSV_EXTENSION = "csv";
    /** Constant for zip mime type. */
    public static final String MIME_TYPE_ZIP = "application/zip";
    /** Constant for zip mime type extension. */
    public static final String MIME_TYPE_ZIP_EXTENSION = "zip";
    /** Constant for content type header. */
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    /** Constant for content length header. */
    public static final String HEADER_CONTENT_LENGTH = "Content-Length";
    /** Constant for transfer encoding header. */
    public static final String HEADER_TRANSFER_ENCODING = "Transfer-Encoding";
    /** Constant for a transfer encoding header of chunked. */
    public static final String HEADER_TRANSFER_ENCODING_CHUNKED = "chunked";
    /** Constant for a targetnamespace header. */
    public static final String HEADER_TARGET_NAMESPACE = "targetnamespace";
    /** Constant for a messageid header. */
    public static final String HEADER_MESSAGE_ID = "messageid";
    /** Constant for tansport prefix header. */
    public static final String HEADER_TRANSPORT_PREFIX = "@transport";
    /** Constant for service prefix header. */
    public static final String HEADER_ROUTER_PREFIX = "@router";
    /** Constant for router trace level header. */
    public static final String HEADER_TRACE_LEVEL = "tracelevel";
    /** Constant for router relates to header. */
    public static final String HEADER_RELATES_TO = "relatesto";
    /** Constant for http transport. */
    public static final String TRANSPORT_NAME_HTTP = "HTTP";
    /** Constant for jms transport. */
    public static final String TRANSPORT_NAME_JMS = "JMS";
    /** Constant for file transport. */
    public static final String TRANSPORT_NAME_FILE = "FILE";
    /** Constant for direct transport. */
    public static final String TRANSPORT_NAME_DIRECT = "DIRECT";
    /** Constant for target namespace query parameter. */
    public static final String QUERY_PARAM_TARGET_NAMESPACE = "targetNamespace";
    /** Constant for defining ws addressing namespace. */
    public static final String WS_ADDRESSING_NAMESPACE = "http://www.w3.org/2005/08/addressing";
    /** Constant to define the string representation of a SOAP service type. */
    public static final String SERVICE_TYPE_SOAP = "SOAP";
    /** Constant to define the string representation of a REST service type. */
    public static final String SERVICE_TYPE_REST = "REST";
    /** Constant to define the empty Persistence string. */
    public static final String PERSISTENCE_EMPTY_STRING = "NA";
    /** Constant holding the localhost address. */
    public static final String LOCALHOST = "127.0.0.1";
    /** Constant for the subsystem to use for the loging service. */
    public static final String LOGGING_SUBSYSTEM = "IE_SF";
    /** Constant defining the maximum length for comment-style columns. */
    public static final int COMMENT_FIELD_SIZE = 4000;

}
