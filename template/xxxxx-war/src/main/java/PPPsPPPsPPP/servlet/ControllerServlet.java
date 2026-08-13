
package ppp.ppp.ppp.servlet;

import java.io.IOException;
//import java.util.logging.Logger;
import java.util.Set;
import java.lang.Class;
import java.lang.Object;
import java.lang.reflect.Method;

import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
___JSP_REPORT_IMPORT_CONST_FUNCTION___

/**
 * Lllll
 *
 * This is the central Front Controller servlet (ControllerServlet).
 * It is mapped to /do and acts as the single entry point for most application requests.
 * Main responsibilities:
 * - Reads the op request parameter (defaults to "Home" if missing).
 * - Handles a special "logout" operation (invalidates the session and redirects).
 * - Looks up the requested operation in ControllerEnums.Calls.
 * - Uses reflection to invoke the corresponding method on the appropriate servlet
 *   (itself, EventManagerServlet, EmailSubsServlet, SmsSubsServlet, DownloadFileServlet,
 *   or other injected servlets via code-generation placeholders).
 * - Sets the current user role (GUEST / USER / ADMIN) as a request attribute.
 * - Forwards to index.jsp (except for file downloads).
 * It also contains a simple doPass method used by some operations that only need
 * to pass the op value through.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@WebServlet("/do")
@ServletSecurity(@HttpConstraint(rolesAllowed = { "ADMIN", "USER", "GUEST" }))
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    EventManagerServlet eventManagerServlet;

    @Inject
    EmailSubsServlet emailSubsServlet;

    @Inject
    SmsSubsServlet smsSubsServlet;

    @Inject
    DownloadFileServlet downloadFileServlet;

___CONTROLLER_INJECT_REPORT_FUNCTION___

___CONTROLLER_INJECT_FUNCTION___

    /**
     * Default constructor.
     */
    public ControllerServlet() {
        // TODO Auto-generated constructor stub
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {  
        doPost(request, response);  
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String op = request.getParameter("op");
            log("\n*****************Op = " + op);

            if (op == null)
              op = "Home";

            if (op.equals("logout")) {
                log("User requested logout");
                response.setHeader("Cache-Control", "no-cache, no-store");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Expires", new java.util.Date().toString());
                if (request.getSession(false) != null) {
                    log("\n***************** SESSION = invalidate()");
                    request.getSession(false).invalidate();
                    request.logout();
                }
                response.sendRedirect(request.getContextPath() + "/do?op=Home");
                return;
            }

            Class aClass = ControllerEnums.Calls.get(op).getServlet();
            String fullClassName = aClass.getName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String sMethod = ControllerEnums.Calls.get(op).getMethod();
            log("\n*****************className = " + className);

            String role = "GUEST";
            if (request.isUserInRole("USER"))
                role = "USER";
            if (request.isUserInRole("ADMIN"))
                role = "ADMIN";
            request.setAttribute("role", role);
            log("\n*****************role = " + role);

            Method aMethod = aClass.getDeclaredMethod(sMethod, HttpServletRequest.class, HttpServletResponse.class);

            if (className.equals("ControllerServlet")) {
               aMethod.invoke(this, request, response);
            }
            else if (className.equals("EventManagerServlet")) {
              aMethod.invoke(eventManagerServlet, request, response);
            }
            else if (className.equals("EmailSubsServlet")) {
              aMethod.invoke(emailSubsServlet, request, response);
            }
            else if (className.equals("SmsSubsServlet")) {
              aMethod.invoke(smsSubsServlet, request, response);
            }
            else if (className.equals("DownloadFileServlet")) {
              aMethod.invoke(downloadFileServlet, request, response);
            }
___CONTROLLER_INVOKE_REPORT_FUNCTION___
___CONTROLLER_INVOKE_FUNCTION___

___CONTROLLER_SYNC_CAMERA___
___CONTROLLER_SYNC_VIDEO___
___CONTROLLER_SYNC_THUMBNAIL___
___CONTROLLER_SYNC_POST___

            if (!className.equals("DownloadFileServlet")) {
                RequestDispatcher resultView = request.getRequestDispatcher("index.jsp");
                resultView.forward(request, response);
            }

        } catch (Exception e) {

            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }

            log("\nError============>" + t.getMessage());
            e.printStackTrace();
        }
    }

    public void doPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         String op = request.getParameter("op");
         request.setAttribute("op", op);
    }

}
