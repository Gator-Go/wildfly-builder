package ppp.ppp.ppp.servlet.helper;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.logging.Logger;

/**
 * Lllll
 *
 * This is the validation helper class for Admin Events (EventErrorHelper).
 * It validates data submitted from the Event Manager forms.
 * It also contains small utility methods (checkData, checkInteger, isInteger) used by the
 * above checks. Returns true when errors are found and sets corresponding error messages on the request.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@RequestScoped
public class EventErrorHelper
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
    public boolean checkAdminEventData(HttpServletRequest req)
    {
        boolean errors = false;

        String name = req.getParameter("name");
        if (checkData(name, "name", "Name cannot be empty", req) == true)
        {
            errors = true;
        }

        String description = req.getParameter("description");
        if (checkData(description, "description", "Description cannot be empty", req) == true)
        {
            errors = true;
        }

        int paramRows = Integer.parseInt(req.getParameter("nbrParam"));
        for ( int x = 0; x < paramRows; x++) {
          String nameId = "name" + x;
          String paramName = req.getParameter(nameId);
          if (paramName.trim().isEmpty()) {
             req.setAttribute("msg", "All Parameters must have a Name and Description");
             errors = true;
             break;
          }
          String descriptionId = "description" + x;
          String paramDescription = req.getParameter(descriptionId);
          if (paramDescription.trim().isEmpty()) {
             req.setAttribute("msg", "All Parameters must have a Name and Description");
             errors = true;
             break;
          }
        }

        return errors;
    }

    /**
     * This method checks for valid seasonality data from a context key-values object.<br>
     * If invalid data is found, an error message is loaded in the context.
     *
     * @param req A <code>DataBus</code> object containing the data.
     * @return <code>boolean</code> that indicates if an error has been found.
     */
    public boolean checkAdminEventFire(HttpServletRequest req)
    {
        boolean errors = false;

        int paramRows = Integer.parseInt(req.getParameter("nbrParam"));
        for ( int x = 0; x < paramRows; x++) {

          String requiredId = "required" + x;
          String required = req.getParameter(requiredId);

          String fireValueId = "fireValue" + x;
          String fireValue = req.getParameter(fireValueId);

          if (required.equals("true") && fireValue.trim().isEmpty()) {
             req.setAttribute("msg", "Required Parameters must have a Value");
             errors = true;
             break;
          }
        }

        return errors;
    }


    /**
     * This method checks for valid seasonality data from a context key-values object.<br>
     * If invalid data is found, an error message is loaded in the context.
     *
     * @param req A <code>DataBus</code> object containing the data.
     * @return <code>boolean</code> that indicates if an error has been found.
     */
    public boolean checkAdminEventSchedule(HttpServletRequest req)
    {
        boolean errors = false;

        String second = req.getParameter("second");
        if (checkData(second, "second", "Second cannot be empty", req) == true)
        {
            errors = true;
        }

        String minute = req.getParameter("minute");
        if (checkData(minute, "minute", "Minute cannot be empty", req) == true)
        {
            errors = true;
        }

        String hour = req.getParameter("hour");
        if (checkData(hour, "hour", "Hour cannot be empty", req) == true)
        {
            errors = true;
        }

        String dom = req.getParameter("dom");
        if (checkData(dom, "dom", "Day of Month cannot be empty", req) == true)
        {
            errors = true;
        }

        String month = req.getParameter("month");
        if (checkData(month, "month", "Month cannot be empty", req) == true)
        {
            errors = true;
        }

        String dow = req.getParameter("dow");
        if (checkData(dow, "dow", "Day of Week cannot be empty", req) == true)
        {
            errors = true;
        }

        String year = req.getParameter("year");
        if (checkData(year, "year", "Year cannot be empty", req) == true)
        {
            errors = true;
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



    /**
     * This method checks for valid id data. If invalid data is found,<br>
     * an error message is loaded in the context and a "true" value is returned.
     *
     * @param id A <code>String</code> object containing the id data.
     * @param req A <code>DataBus</code> object containing the error message data.
     * @return <code>boolean</code> value "true" indicates an error has been found.
     */
    public boolean checkInteger(String data, String dataName, HttpServletRequest req)
    {
        boolean errors = false;

        if (isInteger(data) == false)
        {
            req.setAttribute(dataName + "Error", dataName + " must be numeric");
            errors = true;
        }

        return errors;
    }

    public static boolean isInteger(String str)
    {
        boolean valid = false;
        try
        {
            Integer.parseInt(str);
            valid = true;
        }
        catch (NumberFormatException e)
        {
            valid = false;
        }
        return valid;
    }


}
