
package ppp.ppp.ppp.servlet;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.lang.Class;

public class ControllerEnums {
    private static final long serialVersionUID = 1L;

    public enum Calls {

___CONTROLLER_ENUMS_SYNC_FUNCTION___

        TEST("Test",ControllerServlet.class,"doTest","0","x","blank.jsp","blank.jsp");

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
