
package ppp.ppp.ppp.servlet;

import ppp.ppp.ppp.entity.UserSms;
import ppp.ppp.ppp.service.SmsSubsService;
import ppp.ppp.ppp.servlet.helper.SmsSubsDataHelper;
import ppp.ppp.ppp.servlet.helper.SmsSubsErrorHelper;

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
 * This is the SMS Subscriptions management servlet (SmsSubsServlet).
 * It handles all CRUD operations for UserSms (SMS subscription) records and is called
 * by the central ControllerServlet.
 * It uses helper classes (SmsSubsDataHelper and SmsSubsErrorHelper) for form data loading
 * and validation, and delegates persistence to SmsSubsService.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@RequestScoped
public class SmsSubsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private Logger log;

    @Inject
    private SmsSubsService SmsSubsService;

    @Inject
    private SmsSubsDataHelper dataHelper;

    @Inject
    private SmsSubsErrorHelper errorHelper;



    /**
     * Default constructor.
     */
    public SmsSubsServlet() {
        // TODO Auto-generated constructor stub
    }



    public void doHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<UserSms> userSmss = SmsSubsService.findAllUserSmss();
        if (userSmss == null || userSmss.size() == 0)
        {
            String alertMsg = "UserSms search and results returned null or 0 userSmss";
            log.warning(alertMsg);
            req.setAttribute("op", "AdminSmsSubs");
            return;
        }

        req.setAttribute("userSmss", userSmss);
        req.setAttribute("op", "AdminSmsSubs");
    }


   /**
    * The doViewAdminSmsSubs method calls the AdminSmsSubs view web page.
    * It loads AdminSmsSubs data to the web page.
    *
    * @param req A HttpServletRequest object containing the request data.
    * @param resp A HttpServletResponse object containing the response data.
    */

    public void doViewSmsSubs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userSmsId = new Long(req.getParameter("id"));
        UserSms userSms = SmsSubsService.getUserSms(userSmsId);

        req.setAttribute("smsSubs", userSms);
        req.setAttribute("op", "ViewSmsSubs");
    }


   /**
    * The doAddSmsSubs method calls the User SmsSubs Add web page.
    * It loads default data to the web page.
    *
    * @param req A HttpServletRequest object containing the request data.
    * @param resp A HttpServletResponse object containing the response data.
    */
    public void doAddSmsSubs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        dataHelper.loadEventSmsSubsData(req);
        req.setAttribute("opAction", "AddSmsSubsAction");
        req.setAttribute("opBack", "AdminSmsSubs");
        req.setAttribute("op", "AddSmsSubs");
        req.setAttribute("returnOp", "AddSmsSubs");
        req.setAttribute("returnAction", "AddSmsSubsAction");
    }

   /**
    * The doAddSmsSubsSelection method adds a selection to the User SmsSubs Add web page.
    * It loads user data to the web page.
    *
    * @param req A HttpServletRequest object containing the request data.
    * @param resp A HttpServletResponse object containing the response data.
    */
    public void doAddSmsSubsSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String returnOp = req.getParameter("returnOp");
        String returnAction = req.getParameter("returnAction");
        String newSmsSubsId = req.getParameter("smsSubsId");
        dataHelper.loadSmsSubsDataFromReq(req, newSmsSubsId);
        dataHelper.loadEventSmsSubsData(req);

        req.setAttribute("opBack", "AdminSmsSubs");
        req.setAttribute("op", returnOp);
        req.setAttribute("opAction", returnAction);

    }

   /**
    * The doAddSmsSubsAction method creates the AdminSmsSubs from add web page data.
    *
    * @param req A HttpServletRequest object containing the request data.
    * @param resp A HttpServletResponse object containing the response data.
    */

    public void doAddSmsSubsAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (errorHelper.checkSmsSubsData(req) == true)
        {
            dataHelper.loadSmsSubsDataFromReq(req, null);
            dataHelper.loadEventSmsSubsData(req);
            req.setAttribute("opAction", "AddSmsSubsAction");
            req.setAttribute("opBack", "AdminSmsSubs");
            req.setAttribute("op", "AddSmsSubs");
            return;
        }

        UserSms userSms = new UserSms();
        dataHelper.loadUserSms(userSms, req);

        Long userSmsId = SmsSubsService.addUserSms(userSms);

        req.setAttribute("msg", "User SmsSubs Record Added, ID = " + userSmsId);
        doHome(req, resp);
    }


   /**
    * The doEditSmsSubs method calls the AdminSmsSubs edit web page.
    * It loads default data to the web page.
    *
    * @param req A HttpServletRequest object containing the request data.
    * @param resp A HttpServletResponse object containing the response data.
    */

    public void doEditSmsSubs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userSmsId = new Long(req.getParameter("id"));
        UserSms userSms = SmsSubsService.getUserSms(userSmsId);
        dataHelper.loadSmsSubsFormData(userSms, req);
        dataHelper.loadEventSmsSubsData(req);

        req.setAttribute("opAction", "EditSmsSubsAction");
        req.setAttribute("opBack", "AdminSmsSubs");
        req.setAttribute("op", "EditSmsSubs");
        req.setAttribute("returnOp", "EditSmsSubs");
        req.setAttribute("returnAction", "EditSmsSubsAction");
    }

   /**
    * The doEditSmsSubsAction method creates the AdminSmsSubs from edit web page data.
    *
    * @param req A HttpServletRequest object containing the request data.
    * @param resp A HttpServletResponse object containing the response data.
    */

    public void doEditSmsSubsAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (errorHelper.checkSmsSubsData(req) == true)
        {
            dataHelper.loadSmsSubsDataFromReq(req, null);
            dataHelper.loadEventSmsSubsData(req);
            req.setAttribute("opAction", "EditSmsSubsAction");
            req.setAttribute("opBack", "AdminSmsSubs");
            req.setAttribute("op", "EditSmsSubs");
            return;
        }

        Long userSmsId = new Long(req.getParameter("id"));
        UserSms userSms = SmsSubsService.getUserSms(userSmsId);

        dataHelper.loadUserSms(userSms, req);
        String result = SmsSubsService.editUserSms(userSms);

        if (!result.equals("Success"))
        {
            req.setAttribute("msg", "User SmsSubs Edit Error " + result);
            log.warning("result = " + result);
        }
        else
            req.setAttribute("msg", "User SmsSubs Record Edited Successfully");

        doHome(req, resp);
    }


   /**
    * The doDeleteSmsSubs method calls the UserSms delete web page.
    * It loads UserSms data to the web page.
    *
    * @param req A HttpServletRequest object containing the request data.
    * @param resp A HttpServletResponse object containing the response data.
    */
    public void doDeleteSmsSubs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userSmsId = new Long(req.getParameter("id"));
        UserSms userSms = SmsSubsService.getUserSms(userSmsId);
        req.setAttribute("smsSubs", userSms);

        req.setAttribute("opAction", "DeleteSmsSubsAction");
        req.setAttribute("opBack", "AdminSmsSubs");
        req.setAttribute("op", "DeleteSmsSubs");
    }


   /**
    * The doDeleteSmsSubsAction method deletes the UserSms from delete web page data.
    *
    * @param req A HttpServletRequest object containing the request data.
    * @param resp A HttpServletResponse object containing the response data.
    */
    public void doDeleteSmsSubsAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userSmsId = new Long(req.getParameter("id"));

        String result = SmsSubsService.deleteUserSms(userSmsId);
        if (!result.equals("Success"))
        {
            req.setAttribute("msg", "User SmsSubs Delete Error " + result);
            log.warning("result = " + result);
        }
        else
            req.setAttribute("msg", "User SmsSubs Record Deleted Successfully");

        doHome(req, resp);
    }



}
