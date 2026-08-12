
package ppp.ppp.ppp.report;

import java.text.SimpleDateFormat;

/**
 * Lllll
 *
 * This is a constants class for the report module.
 * It defines:
 * - Operation/page names used for navigation and actions (e.g. report list,
 *   generate report, admin add/edit/view/delete screens and their corresponding
 *   action handlers).
 * - Date/time formatters (SimpleDateFormat instances) covering various styles:
 *   standard date/time, day-of-year (DOY) formats, CMS/archive/directory variants, etc.
 * - Common request/parameter names and string literals used in forms and UI logic
 *   (e.g. op, id, name, enabled, true/false, pagination keys, etc.).
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

public class ConstRpt
{
    public static final String OP_HOME = "RptListList";
    public static final String OP_REPORT_LIST = "RptListList";
    public static final String OP_LIST_RPT = "ListRpt";
    public static final String OP_GEN_RPT = "GenRpt";
    public static final String OP_GEN_RPT_ACTION = "GenRptAction";

    public static final String OP_ADMIN_HOME = "AdminReports";
    public static final String OP_ADMIN_RPT_ADD = "AdminRptAdd";
    public static final String OP_ADMIN_RPT_ADD_ACTION = "AdminRptAddAction";
    public static final String OP_ADMIN_RPT_VIEW = "AdminRptView";
    public static final String OP_ADMIN_RPT_EDIT = "AdminRptEdit";
    public static final String OP_ADMIN_RPT_EDIT_ACTION = "AdminRptEditAction";
    public static final String OP_ADMIN_RPT_DELETE = "AdminRptDelete";
    public static final String OP_ADMIN_RPT_DELETE_ACTION = "AdminRptDeleteAction";

   public static final SimpleDateFormat formatDate = new SimpleDateFormat ("yyyy/MM/dd");
   public static final SimpleDateFormat formatDateTime = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss.SSS");
   public static final SimpleDateFormat formatDateTimeModel = new SimpleDateFormat ("yyyyMMdd HH:mm:ss.SSS");
   public static final SimpleDateFormat formatDateTimeNoMillis = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
   public static final SimpleDateFormat formatDOYDate = new SimpleDateFormat ("yyyy/DDD");
   public static final SimpleDateFormat formatDOYDateHourMinute = new SimpleDateFormat ("yyyy/DDD HH:mm");
   public static final SimpleDateFormat formatDOYDateTime = new SimpleDateFormat ("yyyy/DDD HH:mm:ss.SSS");
   public static final SimpleDateFormat formatDOYDateTimeNoMillis = new SimpleDateFormat ("yyyy/DDD HH:mm:ss");
   public static final SimpleDateFormat formatDOYDateForCMS = new SimpleDateFormat ("yyyyDDD-HHmmss");
   public static final SimpleDateFormat formatDOYDateForCCPArchive = new SimpleDateFormat ("yyyyDDDHH");
   public static final SimpleDateFormat formatDOYDateForCCPDir = new SimpleDateFormat ("yyyy/yyyyDDDHH");
   public static final SimpleDateFormat formatDateYear = new SimpleDateFormat ("yyyy");

    public static final String OP = "op";
    public static final String OP_SUB = "subop";
    public static final String OP_NEW = "new";
    public static final String OP_ACTION = "opAction";
    public static final String OP_BACK = "opBack";
    public static final String OP_NEXT_PAGE = "nextPage";
    public static final String OP_PREVIOUS_PAGE = "previousPage";
    public static final String PAGE = "page";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String TITLE = "title";

    public static final String MINUTE = "minute";
    public static final String HOUR = "hour";
    public static final String DOM = "dom";
    public static final String MONTH = "month";
    public static final String DOW = "dow";
    public static final String ENABLED = "enabled";
    public static final String ENABLED_BOX = "enabledBox";

    public static final String MSG = "msg";
    public static final String ERROR = "Error";
    public static final String EMPTY = "";
    public static final String ONE_INT = "1";
    public static final String SELECTED_VALUE = "selected";
    public static final String CHECKED = "checked";
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String NBR_PARAM = "nbrParam";
    public static final String PARAMETER_FMTS = "parameterFmts";
}
