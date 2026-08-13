package ppp.ppp.ppp.servlet.helper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.logging.Logger;

import java.util.Set;
import java.util.HashSet;

/**
 * Lllll
 *
 * This is a validation helper class for SMS Subscriptions (SmsSubsErrorHelper).
 * It checks the data submitted from the SMS Subscription add/edit forms.
 * If any validation fails, it sets error messages as request attributes and returns
 * true (indicating errors were found).
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@RequestScoped
public class SmsSubsErrorHelper
{
    @Inject
    private Logger logger;

    /**
     * This method checks for valid seasonality data from a context key-values object.<br>
     * If invalid data is found, an error message is loaded in the context.
     *
     * @param req A <code>DataBus</code> object containing the data.
     * @return <code>boolean</code> that indicates if an error has been found.
     */

    public boolean checkSmsSubsData(HttpServletRequest req)
    {
        boolean errors = false;

        String firstName = req.getParameter("firstName");
        if (checkData(firstName, "firstName", "First Name cannot be empty", req) == true)
        {
            errors = true;
        }

        String lastName = req.getParameter("lastName");
        if (checkData(lastName, "lastName", "Last Name cannot be empty", req) == true)
        {
            errors = true;
        }

        String sms = req.getParameter("sms");
        if (checkData(sms, "sms", "Sms cannot be empty", req) == true)
        {
            errors = true;
        }


        Set ids = new HashSet();
        int opRows = Integer.parseInt(req.getParameter("nbrOp"));

        for ( int x = 0; x < opRows; x++) {

          String idId = "id" + x;
          String id = req.getParameter(idId);
          if (ids.add(id) == false) {
             req.setAttribute("msg", "Duplicate SmsSubs on Row " + (x + 1));
             errors = true;
             break;
          }
        }

        return errors;
    }


    /**
     * This method checks for valid data. If no data is found,<br>
     * an error message is loaded in the context and a "true" value is returned.
     *
     * @param data A <code>String</code> object containing the data.
     * @param dataName A <code>String</code> object containing the data name.
     * @param errorMsg A <code>String</code> object containing the message to be displayed as an error.
     * @param req A <code>DataBus</code> object containing the error message data.
     * @return <code>boolean</code> value "true" indicates an error has been found.
     */
    public boolean checkData(String data, String dataName, String errorMsg, HttpServletRequest req)
    {
        boolean errors = false;

        if (data.trim().isEmpty())
        {
            req.setAttribute(dataName + "Error", errorMsg);
            errors = true;
        }
        return errors;
    }

}
