
package ppp.ppp.ppp.servlet;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.lang.Class;
___JSP_REPORT_IMPORT_CONST_FUNCTION___

public class ControllerEnums {
    private static final long serialVersionUID = 1L;

    public enum Calls {

        HOME("Home",ControllerServlet.class,"doPass","0","x","home.jsp","blank.jsp"),

        ADMIN_LOGS("AdminLogs",ControllerServlet.class,"doPass","1","0","alert.jsp","adminIndex.jsp"),

        ADMIN_EVENTS("AdminEvents",EventManagerServlet.class,"doHome","1","1","event/EventList.jsp","adminIndex.jsp"),
        ADMIN_EVENTS_ADD_EVENT("AddEvent",EventManagerServlet.class,"doAddAdminEvent","1","1","event/EventAdd.jsp","adminIndex.jsp"),
        ADMIN_EVENTS_ADD_EVENT_ACTION("AddEventAction",EventManagerServlet.class,"doAddAdminEventAction","1","1","event/EventAdd.jsp","adminIndex.jsp"),
        ADMIN_EVENTS_EDIT_EVENT("EditEvent",EventManagerServlet.class,"doEditAdminEvent","1","1","event/EventEdit.jsp","adminIndex.jsp"),
        ADMIN_EVENTS_EDIT_EVENT_ACTION("EditEventAction",EventManagerServlet.class,"doEditAdminEventAction","1","1","event/EventEdit.jsp","adminIndex.jsp"),
        ADMIN_EVENTS_DELETE_EVENT("DeleteEvent",EventManagerServlet.class,"doDeleteAdminEvent","1","1","event/EventDelete.jsp","adminIndex.jsp"),
        ADMIN_EVENTS_DELETE_EVENT_ACTION("DeleteEventAction",EventManagerServlet.class,"doDeleteAdminEventAction","1","1","event/EventDelete.jsp","adminIndex.jsp"),
        ADMIN_EVENTS_FIRESCHEDULE_EVENT("FireEvent",EventManagerServlet.class,"doFireEvent","1","1","event/EventFire.jsp","adminIndex.jsp"),
        ADMIN_EVENTS_FIRE_EVENT("FireEvent",EventManagerServlet.class,"doFireEvent","1","1","event/EventFire.jsp","adminIndex.jsp"),
        ADMIN_EVENTS_FIRE_EVENT_ACTION("FireEventAction",EventManagerServlet.class,"doFireEventAction","1","1","event/EventFire.jsp","adminIndex.jsp"),
        ADMIN_EVENTS_SCHEDULE_EVENT("ScheduleEvent",EventManagerServlet.class,"doScheduleEvent","1","1","event/EventSchedule.jsp","adminIndex.jsp"),
        ADMIN_EVENTS_SCHEDULE_EVENT_ACTION("ScheduleEventAction",EventManagerServlet.class,"doScheduleEventAction","1","1","event/EventSchedule.jsp","adminIndex.jsp"),

        ADMIN_EMAIL_SUBS("AdminEmailSubs",EmailSubsServlet.class,"doHome","1","2","emailSubs/EmailSubsList.jsp","adminIndex.jsp"),
        ADMIN_EMAIL_SUBS_VIEW("ViewEmailSubs",EmailSubsServlet.class,"doViewEmailSubs","1","2","emailSubs/EmailSubsView.jsp","adminIndex.jsp"),
        ADMIN_EMAIL_SUBS_ADD("AddEmailSubs",EmailSubsServlet.class,"doAddEmailSubs","1","2","emailSubs/EmailSubsAdd.jsp","adminIndex.jsp"),
        ADMIN_EMAIL_SUBS_ADD_SELECT("AddEmailSubsSelect",EmailSubsServlet.class,"doAddEmailSubsSelect","1","2","emailSubs/EmailSubsAdd.jsp","adminIndex.jsp"),
        ADMIN_EMAIL_SUBS_ADD_ACTION("AddEmailSubsAction",EmailSubsServlet.class,"doAddEmailSubsAction","1","2","emailSubs/EmailSubsAdd.jsp","adminIndex.jsp"),
        ADMIN_EMAIL_SUBS_EDIT("EditEmailSubs",EmailSubsServlet.class,"doEditEmailSubs","1","2","emailSubs/EmailSubsEdit.jsp","adminIndex.jsp"),
        ADMIN_EMAIL_SUBS_EDIT_ACTION("EditEmailSubsAction",EmailSubsServlet.class,"doEditEmailSubsAction","1","2","emailSubs/EmailSubsEdit.jsp","adminIndex.jsp"),
        ADMIN_EMAIL_SUBS_DELETE("DeleteEmailSubs",EmailSubsServlet.class,"doDeleteEmailSubs","1","2","emailSubs/EmailSubsDelete.jsp","adminIndex.jsp"),
        ADMIN_EMAIL_SUBS_DELETE_ACTION("DeleteEmailSubsAction",EmailSubsServlet.class,"doDeleteEmailSubsAction","1","2","emailSubs/EmailSubsDelete.jsp","adminIndex.jsp"),

        ADMIN_SMS_SUBS("AdminSmsSubs",SmsSubsServlet.class,"doHome","1","3","smsSubs/SmsSubsList.jsp","adminIndex.jsp"),
        ADMIN_SMS_SUBS_VIEW("ViewSmsSubs",SmsSubsServlet.class,"doViewSmsSubs","1","3","smsSubs/SmsSubsView.jsp","adminIndex.jsp"),
        ADMIN_SMS_SUBS_ADD("AddSmsSubs",SmsSubsServlet.class,"doAddSmsSubs","1","3","smsSubs/SmsSubsAdd.jsp","adminIndex.jsp"),
        ADMIN_SMS_SUBS_ADD_SELECT("AddSmsSubsSelect",SmsSubsServlet.class,"doAddSmsSubsSelect","1","3","smsSubs/SmsSubsAdd.jsp","adminIndex.jsp"),
        ADMIN_SMS_SUBS_ADD_ACTION("AddSmsSubsAction",SmsSubsServlet.class,"doAddSmsSubsAction","1","3","smsSubs/SmsSubsAdd.jsp","adminIndex.jsp"),
        ADMIN_SMS_SUBS_EDIT("EditSmsSubs",SmsSubsServlet.class,"doEditSmsSubs","1","3","smsSubs/SmsSubsEdit.jsp","adminIndex.jsp"),
        ADMIN_SMS_SUBS_EDIT_ACTION("EditSmsSubsAction",SmsSubsServlet.class,"doEditSmsSubsAction","1","3","smsSubs/SmsSubsEdit.jsp","adminIndex.jsp"),
        ADMIN_SMS_SUBS_DELETE("DeleteSmsSubs",SmsSubsServlet.class,"doDeleteSmsSubs","1","3","smsSubs/SmsSubsDelete.jsp","adminIndex.jsp"),
        ADMIN_SMS_SUBS_DELETE_ACTION("DeleteSmsSubsAction",SmsSubsServlet.class,"doDeleteSmsSubsAction","1","3","smsSubs/SmsSubsDelete.jsp","adminIndex.jsp"),

___CONTROLLER_ENUMS_ADMIN_FUNCTION___

___CONTROLLER_ENUMS_FUNCTION___
___CONTROLLER_REPORT_FUNCTION___

        FILE_DOWNLOAD("FileDownload",DownloadFileServlet.class,"doFileDownload","0","x","FileExport.jsp","blank.jsp");

        private static final Map<String, Calls> lookup = new HashMap<String, Calls>();

        static {
            for (Calls s : EnumSet.allOf(Calls.class)) {
                lookup.put(s.getCode(), s);
            }
        }
        private String code;
        private Class servlet;
        private String method;
        private String tabIdx;
        private String tabIdx2;
        private String jsp;
        private String indexJsp;

        private Calls(String code, Class servlet, String method, String tabIdx, String tabIdx2, String jsp, String indexJsp) {
            this.code = code;
            this.servlet = servlet;
            this.method = method;
            this.tabIdx = tabIdx;
            this.tabIdx2 = tabIdx2;
            this.jsp = jsp;
            this.indexJsp = indexJsp;
        }

        public String getCode() {
            return code;
        }

        public Class getServlet() {
            return servlet;
        }

        public String getMethod() {
            return method;
        }

        public String getTabIdx() {
            return tabIdx;
        }

        public String getTabIdx2() {
            return tabIdx2;
        }

        public String getJsp() {
            return jsp;
        }

        public String getIndexJsp() {
            return indexJsp;
        }

        public static Calls get(String code) {
            return lookup.get(code);
        }

        public static boolean checkCode(String code) {
            for (Calls values : Calls.values()) {
                if (code.equals(values.getCode())) {
                    return true;
                }
            }

            return false;
        }
    }

}
