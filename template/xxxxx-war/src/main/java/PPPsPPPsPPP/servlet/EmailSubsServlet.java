
package ppp.ppp.ppp.servlet;

import ppp.ppp.ppp.entity.UserEmail;
import ppp.ppp.ppp.service.EmailSubsService;
import ppp.ppp.ppp.servlet.helper.EmailSubsDataHelper;
import ppp.ppp.ppp.servlet.helper.EmailSubsErrorHelper;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Lllll
 *
 * This is the Email Subscriptions management servlet (EmailSubsServlet).
 * It handles all CRUD operations for UserEmail (email subscription) records and is
 * called by the central ControllerServlet.
 * It uses helper classes (EmailSubsDataHelper and EmailSubsErrorHelper) for form data
 * loading and validation, and delegates persistence to EmailSubsService.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@RequestScoped
public class EmailSubsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private Logger log;

    @Inject
    private EmailSubsService EmailSubsService;

    @Inject
    private EmailSubsDataHelper dataHelper;

    @Inject
    private EmailSubsErrorHelper errorHelper;



    /**
     * Default constructor.
     */
    public EmailSubsServlet() {
        // TODO Auto-generated constructor stub
    }



    public void doHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<UserEmail> userEmails = EmailSubsService.findAllUserEmails();
        if (userEmails == null || userEmails.size() == 0)
        {
            String alertMsg = "UserEmail search and results returned null or 0 userEmails";
            log.warning(alertMsg);
            req.setAttribute("op", "AdminEmailSubs");
            return;
        }

        req.setAttribute("userEmails", userEmails);
        req.setAttribute("op", "AdminEmailSubs");
    }


   /**
    * The doViewAdminEmailSubs method calls the AdminEmailSubs view web page.
    * It loads AdminEmailSubs data to the web page.
    *
    * @param req A HttpServletRequest object containing the request data.
    * @param resp A HttpServletResponse object containing the response data.
    */

    public void doViewEmailSubs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userEmailId = new Long(req.getParameter("id"));
        UserEmail userEmail = EmailSubsService.getUserEmail(userEmailId);

        req.setAttribute("emailSubs", userEmail);
        req.setAttribute("op", "ViewEmailSubs");
    }


   /**
    * The doAddEmailSubs method calls the User EmailSubs Add web page.
    * It loads default data to the web page.
    *
    * @param req A HttpServletRequest object containing the request data.
    * @param resp A HttpServletResponse object containing the response data.
    */
    public void doAddEmailSubs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        dataHelper.loadEventEmailSubsData(req);
        req.setAttribute("opAction", "AddEmailSubsAction");
        req.setAttribute("opBack", "AdminEmailSubs");
        req.setAttribute("op", "AddEmailSubs");
        req.setAttribute("returnOp", "AddEmailSubs");
        req.setAttribute("returnAction", "AddEmailSubsAction");
    }

   /**
    * The doAddEmailSubsSelection method adds a selection to the User EmailSubs Add web page.
    * It loads user data to the web page.
    *
    * @param req A HttpServletRequest object containing the request data.
    * @param resp A HttpServletResponse object containing the response data.
    */
    public void doAddEmailSubsSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String returnOp = req.getParameter("returnOp");
        String returnAction = req.getParameter("returnAction");
        String newEmailSubsId = req.getParameter("emailSubsId");
        dataHelper.loadEmailSubsDataFromReq(req, newEmailSubsId);
        dataHelper.loadEventEmailSubsData(req);

        req.setAttribute("opBack", "AdminEmailSubs");
        req.setAttribute("op", returnOp);
        req.setAttribute("opAction", returnAction);

    }

   /**
    * The doAddEmailSubsAction method creates the AdminEmailSubs from add web page data.
    *
    * @param req A HttpServletRequest object containing the request data.
    * @param resp A HttpServletResponse object containing the response data.
    */

    public void doAddEmailSubsAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (errorHelper.checkEmailSubsData(req) == true)
        {
            dataHelper.loadEmailSubsDataFromReq(req, null);
            dataHelper.loadEventEmailSubsData(req);
            req.setAttribute("opAction", "AddEmailSubsAction");
            req.setAttribute("opBack", "AdminEmailSubs");
            req.setAttribute("op", "AddEmailSubs");
            return;
        }

        UserEmail userEmail = new UserEmail();
        dataHelper.loadUserEmail(userEmail, req);

        Long userEmailId = EmailSubsService.addUserEmail(userEmail);

        req.setAttribute("msg", "User EmailSubs Record Added, ID = " + userEmailId);
        doHome(req, resp);
    }


   /**
    * The doEditEmailSubs method calls the AdminEmailSubs edit web page.
    * It loads default data to the web page.
    *
    * @param req A HttpServletRequest object containing the request data.
    * @param resp A HttpServletResponse object containing the response data.
    */

    public void doEditEmailSubs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userEmailId = new Long(req.getParameter("id"));
        UserEmail userEmail = EmailSubsService.getUserEmail(userEmailId);
        dataHelper.loadEmailSubsFormData(userEmail, req);
        dataHelper.loadEventEmailSubsData(req);

        req.setAttribute("opAction", "EditEmailSubsAction");
        req.setAttribute("opBack", "AdminEmailSubs");
        req.setAttribute("op", "EditEmailSubs");
        req.setAttribute("returnOp", "EditEmailSubs");
        req.setAttribute("returnAction", "EditEmailSubsAction");
    }

   /**
    * The doEditEmailSubsAction method creates the AdminEmailSubs from edit web page data.
    *
    * @param req A HttpServletRequest object containing the request data.
    * @param resp A HttpServletResponse object containing the response data.
    */

    public void doEditEmailSubsAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (errorHelper.checkEmailSubsData(req) == true)
        {
            dataHelper.loadEmailSubsDataFromReq(req, null);
            dataHelper.loadEventEmailSubsData(req);
            req.setAttribute("opAction", "EditEmailSubsAction");
            req.setAttribute("opBack", "AdminEmailSubs");
            req.setAttribute("op", "EditEmailSubs");
            return;
        }

        Long userEmailId = new Long(req.getParameter("id"));
        UserEmail userEmail = EmailSubsService.getUserEmail(userEmailId);

        dataHelper.loadUserEmail(userEmail, req);
        String result = EmailSubsService.editUserEmail(userEmail);

        if (!result.equals("Success"))
        {
            req.setAttribute("msg", "User EmailSubs Edit Error " + result);
            log.warning("result = " + result);
        }
        else
            req.setAttribute("msg", "User EmailSubs Record Edited Successfully");

        doHome(req, resp);
    }


   /**
    * The doDeleteEmailSubs method calls the UserEmail delete web page.
    * It loads UserEmail data to the web page.
    *
    * @param req A HttpServletRequest object containing the request data.
    * @param resp A HttpServletResponse object containing the response data.
    */
    public void doDeleteEmailSubs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userEmailId = new Long(req.getParameter("id"));
        UserEmail userEmail = EmailSubsService.getUserEmail(userEmailId);
        req.setAttribute("emailSubs", userEmail);

        req.setAttribute("opAction", "DeleteEmailSubsAction");
        req.setAttribute("opBack", "AdminEmailSubs");
        req.setAttribute("op", "DeleteEmailSubs");
    }


   /**
    * The doDeleteEmailSubsAction method deletes the UserEmail from delete web page data.
    *
    * @param req A HttpServletRequest object containing the request data.
    * @param resp A HttpServletResponse object containing the response data.
    */
    public void doDeleteEmailSubsAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userEmailId = new Long(req.getParameter("id"));

        String result = EmailSubsService.deleteUserEmail(userEmailId);
        if (!result.equals("Success"))
        {
            req.setAttribute("msg", "User EmailSubs Delete Error " + result);
            log.warning("result = " + result);
        }
        else
            req.setAttribute("msg", "User EmailSubs Record Deleted Successfully");

        doHome(req, resp);
    }



}
