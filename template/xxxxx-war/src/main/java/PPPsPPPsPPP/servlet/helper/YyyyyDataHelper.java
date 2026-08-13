
package ppp.ppp.ppp.servlet.helper;

import ppp.ppp.ppp.entity.Yyyyy;
import ppp.ppp.ppp.enums.YyyyyEnum;
import ppp.ppp.ppp.servlet.helper.CameraHelper;
import ppp.ppp.ppp.settings.XxxxxProperty;

___IMPORT_OPTION_SERVLET_ONE2MANY_CHILD___
___IMPORT_OPTION_SERVLET_ONE2MANY_CHILD_ALERT___
___IMPORT_OPTION_ONE2MANY_PARENT___
___IMPORT_OPTION_ONE2MANY_PARENT_ALERT___
___IMPORT_TAG_OPTIONS___

import java.text.SimpleDateFormat;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.lang.NumberFormatException;
import java.math.BigDecimal;

/**
 * Lllll
 *
 * This is the data helper class for the Yyyyy entity (YyyyyDataHelper).
 * It is responsible for transferring data between the HTTP request and Yyyyy objects
 * (and related structures) for the main CRUD and search screens.
 * This class is heavily used by YyyyyServlet.
 *
 * @author Aaaaa
 * @author <a href="mailto:aaaaa@ddddd">Aaaaa</a>
 * @version 1.0
 * @version $Id$
 */

@RequestScoped
public class YyyyyDataHelper {

    @Inject
    @XxxxxProperty(name = "Format.DateStr")
    private String FormatDateStr;

    @Inject
    @XxxxxProperty(name = "Format.DateTimeStr")
    private String FormatDateTimeStr;

    @Inject
    private EntityManager em;

    @Inject
    CameraHelper cameraHelper;


___INJECT_SERVICE_ONE2MANY_CHILD___
___INJECT_SERVICE_ONE2MANY_CHILD_ALERT___
___INJECT_SERVICE_ONE2MANY_PARENT___
___INJECT_SERVICE_ONE2MANY_PARENT_ALERT___
___INJECT_SERVICE_TAG_OPTIONS___

    public boolean loadYyyyy(Yyyyy yyyyy, HttpServletRequest req) {

        boolean okFlag = true;
        SimpleDateFormat formatDate = new SimpleDateFormat (FormatDateStr);
        SimpleDateFormat formatDateTime = new SimpleDateFormat (FormatDateTimeStr);

        yyyyy.setId(new Long(req.getParameter("id")));
        yyyyy.setDeviceId(new Long(req.getParameter("deviceId")));
        yyyyy.setYyyyyId(new Long(req.getParameter("yyyyyId")));
        try {
            yyyyy.setLastUpdate(formatDate.parse(req.getParameter("lastUpdate")));
        } catch(ParseException e) {
            yyyyy.setLastUpdate(new Date());
        }
        yyyyy.setDeleteFlag(new Boolean(req.getParameter("deleteFlag")));

___SET_REQUEST_STRING___
___SET_REQUEST_ENUM___
___SET_REQUEST_TAG___
___SET_REQUEST_CLOB___
___SET_REQUEST_BOOLEAN___
___SET_REQUEST_INTEGER___
___SET_REQUEST_LONG___
___SET_REQUEST_DOUBLE___
___SET_REQUEST_BIG_DECIMAL___
___SET_REQUEST_MONEY___
___SET_REQUEST_LOC___
___SET_REQUEST_CURRENT_LOC___
___SET_REQUEST_DATE___
___SET_REQUEST_DATE_TIME___
___SET_REQUEST_CAMERA___
___SET_REQUEST_VIDEO___
___SET_REQUEST_THUMBNAIL___
___SET_REQUEST_POST___
___SET_REQUEST_ONE2MANY_CHILD___
___SET_REQUEST_ONE2MANY_CHILD_ALERT___
       return okFlag;
    }

    public boolean loadYyyyySearch(Yyyyy yyyyy, Yyyyy yyyyy2, HttpServletRequest req) {

        boolean okFlag = true;
        SimpleDateFormat formatDate = new SimpleDateFormat (FormatDateStr);
        SimpleDateFormat formatDateTime = new SimpleDateFormat (FormatDateTimeStr);


___SET_SEARCH_STRING___
___SET_SEARCH_ENUM___
___SET_SEARCH_TAG___
___SET_SEARCH_CLOB___
___SET_SEARCH_BOOLEAN___
___SET_SEARCH_INTEGER___
___SET_SEARCH_LONG___
___SET_SEARCH_DOUBLE___
___SET_SEARCH_BIG_DECIMAL___
___SET_SEARCH_MONEY___
___SET_SEARCH_LOC___
___SET_SEARCH_CURRENT_LOC___
___SET_SEARCH_DATE___
___SET_SEARCH_DATE_TIME___
___SET_SEARCH_CAMERA___
___SET_SEARCH_VIDEO___
___SET_SEARCH_THUMBNAIL___
___SET_SEARCH_POST___
___SET_SEARCH_ONE2MANY_CHILD___
___SET_SEARCH_ONE2MANY_CHILD_ALERT___

       if ((req.getParameter("deleteFlag") == null || req.getParameter("deleteFlag").trim().isEmpty()) == false) {
           boolean deleteFlagBool = Boolean.parseBoolean(req.getParameter("deleteFlag").trim());
           yyyyy.setDeleteFlag(new Boolean(deleteFlagBool));
       }

       return okFlag;
    }

___LOAD_ENUM_OPTIONS___
___LOAD_TAG_OPTIONS___

}
