
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

/**
 * Lllll
 *
 * This is the main front-controller servlet for the application (mapped to /sync).
 * It is secured so only users with the ADMIN or USER role can access it.
 * How it works:
 * - Both GET and POST are handled by doPost.
 * - It reads the op request parameter (defaults to "Test" if missing).
 * - Looks up the corresponding entry in ControllerEnums.Calls to determine which servlet class
 *   and method should handle the request.
 * - Uses reflection to invoke that method, passing the request and response.
 * - Contains a simple doTest method that just writes "test success".
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@WebServlet("/sync")
@ServletSecurity(@HttpConstraint(rolesAllowed = { "ADMIN", "USER" }))
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
              op = "Test";

            Class aClass = ControllerEnums.Calls.get(op).getServlet();
            String fullClassName = aClass.getName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String sMethod = ControllerEnums.Calls.get(op).getMethod();
            log("\n*****************className = " + className);

            Method aMethod = aClass.getDeclaredMethod(sMethod, HttpServletRequest.class, HttpServletResponse.class);

            if (className.equals("ControllerServlet")) {
               aMethod.invoke(this, request, response);
            }
___CONTROLLER_INVOKE_FUNCTION___

___CONTROLLER_SYNC_CAMERA___
___CONTROLLER_SYNC_VIDEO___
___CONTROLLER_SYNC_THUMBNAIL___
___CONTROLLER_SYNC_POST___

        } catch (Exception e) {

            Throwable t = e;
            while ((t.getCause()) != null) {
                t = t.getCause();
            }

            log("\nError============>" + t.getMessage());
            e.printStackTrace();
        }
    }

    public void doTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                response.getWriter().write("test success");
                return;
    }

}
